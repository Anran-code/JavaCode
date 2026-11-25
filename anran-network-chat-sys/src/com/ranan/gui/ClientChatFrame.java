package com.ranan.gui;

import com.ranan.ClientReaderThread;
import com.ranan.Constant;
import com.ranan.model.PrivateMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.UTFDataFormatException;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/** 群聊窗口：展示聊天记录、在线用户，并提供消息输入和发送按钮。 */
public class ClientChatFrame extends JFrame {
    private final String nickname;
    private final Socket socket;
    private ChatMessagePane chatArea;
    private JTextArea inputArea;
    private JButton sendButton;
    private JLabel onlineCountLabel;
    private JList<String> onlineUserList;
    private DefaultListModel<String> onlineUserModel;
    // 这两个集合只在 Swing 事件线程中操作，以昵称区分会话。
    private final Map<String, PrivateChatFrame> privateFrames = new HashMap<>();
    // 正文已经保存在服务端，未打开窗口时只需要累计未读数量。
    private final Map<String, Integer> unreadMessages = new HashMap<>();

    /** 创建并显示聊天窗口，直接运行 main 方法即可预览。 */
    public ClientChatFrame() {
        this(null, null);
    }

    /** 接收登录时的昵称和连接，后续收发消息继续使用同一个 Socket。 */
    public ClientChatFrame(String nickname, Socket socket) {
        this.nickname = nickname;
        this.socket = socket;
        setTitle(nickname == null ? "局域网聊天室" : "局域网聊天室 - " + nickname);
        setSize(900, 660);
        setMinimumSize(new Dimension(700, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        // 主动 dispose() 关闭聊天窗口时，也要释放连接。
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closeConnection();
                for (PrivateChatFrame frame : new ArrayList<>(privateFrames.values())) {
                    frame.dispose();
                }
                privateFrames.clear();
                unreadMessages.clear();
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
        inputArea.requestFocusInWindow();
        if (socket != null) {
            new ClientReaderThread(socket, this).start();
        }
    }

    /** 在 Swing 事件线程中，用服务端发来的完整名单替换旧名单并更新人数。 */
    public void updateOnlineUsers(List<String> names) {
        SwingUtilities.invokeLater(() -> {
            if (!isDisplayable()) {
                return;
            }
            onlineUserModel.clear();
            for (String name : names) {
                onlineUserModel.addElement(name);
            }
            onlineCountLabel.setText("在线人数：" + names.size());
            // 发信人下线后仍保留有未读消息的条目，避免消息无法打开。
            for (String peer : unreadMessages.keySet()) {
                if (!onlineUserModel.contains(peer)) {
                    onlineUserModel.addElement(peer);
                }
            }
        });
    }

    /** 打开新私聊窗口时查询最近 50 条；重复打开已有窗口则直接复用。 */
    public void openPrivateChat(String peer) {
        if (peer == null || peer.equals(nickname)) {
            return;
        }
        PrivateChatFrame frame = privateFrames.get(peer);
        boolean newWindow = frame == null || !frame.isDisplayable();
        if (newWindow) {
            frame = createPrivateChatFrame(peer);
            privateFrames.put(peer, frame);
            PrivateChatFrame current = frame;
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // 只销毁窗口，历史已在服务端保存；不清空关闭后新收到的未读数。
                    privateFrames.remove(peer, current);
                }
            });
        }
        frame.setVisible(true);
        if (!isConnected()) {
            frame.showDisconnected();
        } else if (newWindow) {
            requestPrivateHistory(peer, frame.getHistoryRequestId());
        }
        onlineUserList.repaint();
        frame.toFront();
    }

    /** 创建独立会话窗口，窗口的复用与未读消息仍由主窗口统一管理。 */
    protected PrivateChatFrame createPrivateChatFrame(String peer) {
        return new PrivateChatFrame(this, peer);
    }

    /** 统一接收线程将私聊分发到对应窗口；窗口没打开时仅缓存并显示未读数。 */
    public void receivePrivateMessage(PrivateMessage message) {
        SwingUtilities.invokeLater(() -> {
            if (!isDisplayable()) {
                return;
            }
            boolean ownMessage = message.getSender().equals(nickname);
            String peer = ownMessage ? message.getRecipient() : message.getSender();
            PrivateChatFrame frame = privateFrames.get(peer);
            if (frame != null && frame.isDisplayable()) {
                frame.appendMessage(message);
            } else if (!ownMessage) {
                unreadMessages.merge(peer, 1, Integer::sum);
                if (!onlineUserModel.contains(peer)) {
                    onlineUserModel.addElement(peer);
                }
                onlineUserList.repaint();
            }
        });
    }

    /** 向服务端请求该会话的历史记录；请求编号对应本次打开的窗口。 */
    private void requestPrivateHistory(String peer, String requestId) {
        new Thread(() -> {
            try {
                if (!isConnected()) {
                    throw new IOException("连接已断开");
                }
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(buffer);
                out.writeInt(Constant.PRIVATE_HISTORY_REQUEST);
                out.writeUTF(peer);
                out.writeUTF(requestId);
                synchronized (socket) {
                    socket.getOutputStream().write(buffer.toByteArray());
                    socket.getOutputStream().flush();
                }
            } catch (IOException e) {
                receivePrivateHistoryError(peer, requestId, "历史记录请求失败，请重新登录后重试。");
                closeConnection();
            }
        }, "private-history-reader").start();
    }

    /** 只处理当前窗口的查询结果，加载成功后再清除未读数量。 */
    public void receivePrivateHistory(String peer, String requestId, List<PrivateMessage> messages) {
        SwingUtilities.invokeLater(() -> {
            PrivateChatFrame frame = privateFrames.get(peer);
            if (isDisplayable() && frame != null && frame.isDisplayable()
                    && frame.getHistoryRequestId().equals(requestId)) {
                frame.loadHistory(messages);
                unreadMessages.remove(peer);
                onlineUserList.repaint();
            }
        });
    }

    /** 读取失败保留未读提示，避免误以为历史已加载成功。 */
    public void receivePrivateHistoryError(String peer, String requestId, String error) {
        SwingUtilities.invokeLater(() -> {
            PrivateChatFrame frame = privateFrames.get(peer);
            if (isDisplayable() && frame != null && frame.isDisplayable()
                    && frame.getHistoryRequestId().equals(requestId)) {
                frame.historyFailed(error);
            }
        });
    }

    /** 私聊对象离线等业务错误只显示提示，不断开整个聊天连接。 */
    public void showPrivateError(String peer, String error) {
        SwingUtilities.invokeLater(() -> {
            if (!isDisplayable()) {
                return;
            }
            PrivateChatFrame frame = privateFrames.get(peer);
            if (frame != null && frame.isDisplayable()) {
                frame.showNotice(error);
            } else {
                chatArea.appendNotice("发给“" + peer + "”的私聊失败：" + error);
            }
        });
    }

    /** 后台发送“私聊编号、正文、接收者昵称”，回调参数为 null 表示已写出。 */
    public void sendPrivateMessage(String peer, String message, Consumer<String> callback) {
        new Thread(() -> {
            String error = null;
            try {
                writeChatPacket(Constant.PRIVATE_CHAT_MESSAGE, message, peer);
            } catch (UTFDataFormatException e) {
                error = "消息太长，请缩短后再发送。";
            } catch (IOException e) {
                error = "发送失败，请重新登录。";
                closeConnection();
            }
            String result = error;
            SwingUtilities.invokeLater(() -> callback.accept(result));
        }, "private-sender").start();
    }

    /** 群聊和多个私聊窗口共用连接；锁住一次完整写入，防止消息字节交错。 */
    private void writeChatPacket(int type, String message, String peer) throws IOException {
        if (!isConnected()) {
            throw new IOException("连接已断开");
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        dos.writeInt(type);
        dos.writeUTF(message);
        if (type == Constant.PRIVATE_CHAT_MESSAGE) {
            dos.writeUTF(peer);
        }
        synchronized (socket) {
            socket.getOutputStream().write(buffer.toByteArray());
            socket.getOutputStream().flush();
        }
    }

    /** 判断本客户端连接是否仍可用于发送。 */
    public boolean isConnected() {
        return socket != null && !socket.isClosed();
    }

    /** 把服务端群聊消息交给共用记录区，在事件线程中刷新。 */
    public void appendMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            if (isDisplayable()) {
                chatArea.appendMessage(message);
            }
        });
    }

    /** 获取输入内容，检查非空后交给后台线程发送，避免网络等待卡住界面。 */
    private void sendGroupMessage() {
        String message = inputArea.getText();
        if (message.isBlank()) {
            JOptionPane.showMessageDialog(this, "请输入消息内容！");
            inputArea.requestFocusInWindow();
            return;
        }
        if (socket == null || socket.isClosed()) {
            JOptionPane.showMessageDialog(this, "当前未连接服务器，请先登录。 ");
            return;
        }

        // 发送期间禁止重复点击和编辑，成功后清空的就是本次发送的内容。
        sendButton.setEnabled(false);
        inputArea.setEditable(false);
        new Thread(() -> writeGroupMessage(message), "client-sender").start();
    }

    /** 按“群聊编号 + UTF 正文”发送；显示消息统一交给服务端广播后的接收分支。 */
    private void writeGroupMessage(String message) {
        boolean sent = false;
        String error = null;
        try {
            // 先在内存里组成完整消息。若正文超过 writeUTF 的长度限制，
            // 就不会只发出了编号，让服务端一直等待正文。
            writeChatPacket(Constant.GROUP_CHAT_MESSAGE, message, null);
            // 不能关闭 Socket 的输出流，后面还需要继续发送消息。
            sent = true;
        } catch (UTFDataFormatException e) {
            error = "消息太长，请缩短后再发送。";
        } catch (IOException e) {
            error = "发送失败，请重新登录。";
            closeConnection();
        }

        boolean success = sent;
        String errorMessage = error;
        SwingUtilities.invokeLater(() -> {
            if (!isDisplayable()) {
                return;
            }
            if (success) {
                inputArea.setText("");
            } else {
                // 失败时保留原文，方便复制或修改后重试。
                JOptionPane.showMessageDialog(this, errorMessage, "发送失败", JOptionPane.ERROR_MESSAGE);
            }
            inputArea.setEditable(true);
            sendButton.setEnabled(!socket.isClosed());
            inputArea.requestFocusInWindow();
        });
    }

    /** 连接断开后清除过期名单，并提示当前已离线；主动关闭窗口时无需提示。 */
    public void showDisconnected() {
        SwingUtilities.invokeLater(() -> {
            if (!isDisplayable()) {
                return;
            }
            onlineUserModel.clear();
            onlineCountLabel.setText("连接已断开");
            sendButton.setEnabled(false);
            chatArea.appendNotice("与服务器的连接已断开，请重新登录。");
            for (PrivateChatFrame frame : privateFrames.values()) {
                frame.showDisconnected();
            }
        });
    }

    /** 关闭聊天窗口持有的连接；无参构造预览界面时没有连接。 */
    private void closeConnection() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** 取得当前用户昵称。 */
    public String getNickname() {
        return nickname;
    }

    /** 取得登录时建立的连接，供后续收发聊天消息。 */
    public Socket getSocket() {
        return socket;
    }

    /** 组装中间聊天区、右侧在线名单与底部消息输入区。 */
    private void init() {
        JPanel contentPanel = new JPanel(new BorderLayout(8, 8));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setContentPane(contentPanel);

        JPanel displayPanel = new JPanel(new BorderLayout(8, 0));
        displayPanel.add(createChatPanel(), BorderLayout.CENTER);
        displayPanel.add(createOnlinePanel(), BorderLayout.EAST);

        contentPanel.add(displayPanel, BorderLayout.CENTER);
        contentPanel.add(createInputPanel(), BorderLayout.SOUTH);
    }

    /** 使用 JTextPane 分别设置消息头和正文的字体、字号及颜色。 */
    private JScrollPane createChatPanel() {
        chatArea = new ChatMessagePane(nickname);

        return new JScrollPane(chatArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    /** 创建右侧在线人数和用户列表，登录后由接收线程提供最新名单。 */
    private JPanel createOnlinePanel() {
        JPanel onlinePanel = new JPanel(new BorderLayout(0, 8));
        onlinePanel.setPreferredSize(new Dimension(180, 0));
        onlinePanel.setBackground(Color.WHITE);
        onlinePanel.setBorder(BorderFactory.createEmptyBorder(10, 8, 8, 8));

        onlineCountLabel = new JLabel("在线人数：0", SwingConstants.CENTER);
        onlineCountLabel.setFont(new Font("楷体", Font.BOLD, 20));

        onlineUserModel = new DefaultListModel<>();
        onlineUserList = new JList<>(onlineUserModel);
        onlineUserList.setFont(new Font("楷体", Font.PLAIN, 20));
        onlineUserList.setFixedCellHeight(34);
        onlineUserList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // 数字只影响显示，模型里仍然保存原昵称，发送时不会误带“(2)”等后缀。
        onlineUserList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                           boolean selected, boolean focus) {
                super.getListCellRendererComponent(list, value, index, selected, focus);
                Integer pending = unreadMessages.get(value.toString());
                setText(value + (pending == null ? "" : " (" + pending + ")"));
                return this;
            }
        });
        bindPrivateChatMenu();

        JScrollPane scrollPane = new JScrollPane(onlineUserList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        onlinePanel.add(onlineCountLabel, BorderLayout.NORTH);
        onlinePanel.add(scrollPane, BorderLayout.CENTER);
        JButton historyButton = new JButton("查看私聊记录");
        historyButton.setFont(new Font("宋体", Font.PLAIN, 14));
        historyButton.addActionListener(e -> {
            String peer = JOptionPane.showInputDialog(this, "输入对方昵称（也可查看离线用户的记录）：");
            if (peer != null && !peer.isBlank()) {
                openPrivateChat(peer.trim());
            }
        });
        onlinePanel.add(historyButton, BorderLayout.SOUTH);
        return onlinePanel;
    }

    /** 在实际点击的用户行旁显示“私聊”；空白处和自己这一行不弹菜单。 */
    private void bindPrivateChatMenu() {
        onlineUserList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { showMenu(e); }
            @Override
            public void mouseReleased(MouseEvent e) { showMenu(e); }

            private void showMenu(MouseEvent e) {
                if (!e.isPopupTrigger()) {
                    return;
                }
                int index = onlineUserList.locationToIndex(e.getPoint());
                if (index < 0 || !onlineUserList.getCellBounds(index, index).contains(e.getPoint())) {
                    return;
                }
                String peer = onlineUserModel.getElementAt(index);
                if (peer.equals(nickname)) {
                    return;
                }
                onlineUserList.setSelectedIndex(index);
                JPopupMenu menu = new JPopupMenu();
                JMenuItem item = new JMenuItem("私聊");
                item.setFont(new Font("宋体", Font.PLAIN, 16));
                item.addActionListener(event -> openPrivateChat(peer));
                menu.add(item);
                menu.show(onlineUserList, e.getX(), e.getY());
            }
        });
    }

    /** 创建底部多行输入框，并给发送按钮绑定群聊发送事件。 */
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setPreferredSize(new Dimension(0, 110));

        inputArea = new JTextArea(3, 20);
        inputArea.setFont(new Font("楷体", Font.PLAIN, 20));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setMargin(new Insets(8, 10, 8, 10));
        inputArea.setToolTipText("在这里输入聊天消息");
        inputPanel.add(new JScrollPane(inputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

        sendButton = new JButton("发送");
        sendButton.addActionListener(e -> sendGroupMessage());
        sendButton.setFont(new Font("楷体", Font.BOLD, 22));
        sendButton.setPreferredSize(new Dimension(104, 44));
        sendButton.setBackground(new Color(0, 145, 150));
        sendButton.setForeground(Color.WHITE);
        sendButton.setOpaque(true);
        sendButton.setBorderPainted(false);
        sendButton.setFocusPainted(false);

        // 用小面板居中放置按钮，避免 BorderLayout 把按钮拉满输入区高度。
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.add(sendButton);
        inputPanel.add(buttonPanel, BorderLayout.EAST);
        return inputPanel;
    }

    /** 取得聊天记录区，供后续追加收到的消息。 */
    public JTextPane getChatArea() {
        return chatArea;
    }

    /** 取得输入区，供后续读取消息和发送后清空输入。 */
    public JTextArea getInputArea() {
        return inputArea;
    }

    /** 取得发送按钮，供后续绑定发送事件。 */
    public JButton getSendButton() {
        return sendButton;
    }

    /** 取得在线用户数据模型，修改模型即可更新右侧列表。 */
    public DefaultListModel<String> getOnlineUserModel() {
        return onlineUserModel;
    }

    /** 取得人数标签，供后续与在线用户列表一起更新。 */
    public JLabel getOnlineCountLabel() {
        return onlineCountLabel;
    }

    /** 在 Swing 事件线程中启动界面；后续网络线程更新组件时也应切回该线程。 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientChatFrame::new);
    }
}
