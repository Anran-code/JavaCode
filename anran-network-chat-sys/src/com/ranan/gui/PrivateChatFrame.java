package com.ranan.gui;

import com.ranan.model.PrivateMessage;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

/** 独立私聊窗口，复用群聊窗口的连接，不创建 Socket 或接收线程。 */
public class PrivateChatFrame extends JFrame {
    private final ClientChatFrame owner;
    private final String peer;
    private final ChatMessagePane chatArea;
    private final JTextArea inputArea = new JTextArea(3, 20);
    private final JButton sendButton = new JButton("发送");
    private final JLabel historyStatus = new JLabel("正在加载最近 50 条私聊记录……");
    private final String historyRequestId = UUID.randomUUID().toString();
    private final TreeMap<Long, PrivateMessage> messages = new TreeMap<>();
    private boolean historyLoading = true;

    /** 创建指定对象的会话；关闭时只销毁窗口，不退出程序或断开连接。 */
    public PrivateChatFrame(ClientChatFrame owner, String peer) {
        this.owner = owner;
        this.peer = peer;
        chatArea = new ChatMessagePane(owner.getNickname());
        setTitle("与" + peer + "私聊 - " + owner.getNickname());
        setSize(720, 560);
        setMinimumSize(new Dimension(520, 400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        setLocationRelativeTo(owner);
    }

    /** 组装聊天记录区、输入区和发送按钮，沿用群聊字体与颜色。 */
    private void init() {
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setContentPane(root);
        historyStatus.setFont(new Font("宋体", Font.PLAIN, 13));
        historyStatus.setForeground(Color.DARK_GRAY);
        root.add(historyStatus, BorderLayout.NORTH);
        root.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        JPanel bottom = new JPanel(new BorderLayout(10, 0));
        bottom.setPreferredSize(new Dimension(0, 110));
        inputArea.setFont(new Font("楷体", Font.PLAIN, 20));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setMargin(new Insets(8, 10, 8, 10));
        bottom.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        sendButton.setFont(new Font("楷体", Font.BOLD, 22));
        sendButton.setPreferredSize(new Dimension(104, 44));
        sendButton.setBackground(new Color(0, 145, 150));
        sendButton.setForeground(Color.WHITE);
        sendButton.setOpaque(true);
        sendButton.setBorderPainted(false);
        sendButton.setFocusPainted(false);
        sendButton.addActionListener(e -> sendMessage());
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.add(sendButton);
        bottom.add(buttons, BorderLayout.EAST);
        root.add(bottom, BorderLayout.SOUTH);
    }

    /** 校验正文并交给主窗口发送，回调在 Swing 事件线程中更新按钮和输入框。 */
    private void sendMessage() {
        String message = inputArea.getText();
        if (message.isBlank()) {
            JOptionPane.showMessageDialog(this, "请输入消息内容！");
            return;
        }
        sendButton.setEnabled(false);
        inputArea.setEditable(false);
        owner.sendPrivateMessage(peer, message, error -> {
            if (!isDisplayable()) {
                return;
            }
            if (error == null) {
                inputArea.setText("");
            } else {
                chatArea.appendNotice(error);
            }
            inputArea.setEditable(true);
            sendButton.setEnabled(owner.isConnected());
            inputArea.requestFocusInWindow();
        });
    }

    /** 显示服务端转发的消息；自己的消息以服务端回传为准，避免重复显示。 */
    public void appendMessage(PrivateMessage message) {
        messages.put(message.getId(), message);
        trimMessages();
        if (!historyLoading) {
            renderMessages();
        }
    }

    /** 合并历史与加载期间到达的实时消息；相同 ID 只保留一份，再按 ID 排序。 */
    public void loadHistory(List<PrivateMessage> history) {
        for (PrivateMessage message : history) {
            messages.put(message.getId(), message);
        }
        trimMessages();
        historyLoading = false;
        historyStatus.setText("最近 50 条私聊记录（含实时消息）");
        renderMessages();
    }

    /** 查询失败时保留已收到的实时消息，并给出明确提示。 */
    public void historyFailed(String reason) {
        historyLoading = false;
        historyStatus.setText(reason);
        renderMessages();
    }

    /** 界面和文件统一只保留当前会话最新的 50 条。 */
    private void trimMessages() {
        while (messages.size() > 50) {
            messages.pollFirstEntry();
        }
    }

    /** 最多重绘 50 条，既保证时间顺序，也避免历史与实时消息重复。 */
    private void renderMessages() {
        chatArea.setText("");
        for (PrivateMessage message : messages.values()) {
            chatArea.appendMessage(message.getDisplayText(), message.getSender().equals(owner.getNickname()));
        }
    }

    /** 显示离线或发送失败提示，不将提示当成聊天正文。 */
    public void showNotice(String message) {
        chatArea.appendNotice(message);
    }

    /** 主连接断开后，本窗口也停止发送。 */
    public void showDisconnected() {
        sendButton.setEnabled(false);
        if (historyLoading) {
            historyFailed("连接已断开，未能加载历史记录");
        }
        showNotice("与服务器的连接已断开，请重新登录。");
    }

    /** 取得本会话的消息记录区。 */
    public ChatMessagePane getChatArea() { return chatArea; }
    /** 取得本会话的消息输入区。 */
    public JTextArea getInputArea() { return inputArea; }
    /** 取得本会话的发送按钮。 */
    public JButton getSendButton() { return sendButton; }
    /** 区分同一对象前后打开的窗口，过期查询不能覆盖新窗口。 */
    public String getHistoryRequestId() { return historyRequestId; }
}
