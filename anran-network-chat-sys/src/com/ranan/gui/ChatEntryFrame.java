package com.ranan.gui;

import com.ranan.Constant;
import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/** 群聊入口：输入昵称后，通过按钮进入聊天室。 */
public class ChatEntryFrame extends JFrame {
    private JTextField nicknameField;
    private JButton enterButton;
    private JButton cancelButton;

    /** 初始化并显示聊天入口窗口。 */
    public ChatEntryFrame() {
        setTitle("局域网聊天室");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        nicknameField.requestFocusInWindow();
    }

    /** 组装居中的昵称输入行和按钮区域，并留出足够的显示空间。 */
    private void init() {
        JPanel contentPanel = new JPanel(new BorderLayout(0, 32));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(32, 40, 32, 40));
        setContentPane(contentPanel);

        contentPanel.add(createFormPanel(), BorderLayout.NORTH);
        contentPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        // 回车等同于点击进入按钮，也会执行登录事件。
        getRootPane().setDefaultButton(enterButton);
    }

    /** 将昵称标签与输入框整体居中，标签按字体自动计算宽度，避免文字截断。 */
    private JPanel createFormPanel() {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        Font formFont = new Font("楷体", Font.PLAIN, 22);

        JLabel nicknameLabel = new JLabel("昵称：", SwingConstants.RIGHT);
        nicknameLabel.setFont(formFont);

        nicknameField = new JTextField();
        nicknameField.setFont(formFont);
        nicknameField.setPreferredSize(new Dimension(240, 46));
        nicknameField.setMargin(new Insets(6, 10, 6, 10));
        nicknameLabel.setLabelFor(nicknameField);

        rowPanel.add(nicknameLabel);
        rowPanel.add(nicknameField);
        return rowPanel;
    }

    /** 创建蓝色进入按钮和红色取消按钮，取消时关闭入口窗口。 */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        enterButton = createButton("进入", new Color(0, 123, 255));
        cancelButton = createButton("取消", new Color(230, 65, 70));
        enterButton.addActionListener(e -> login());
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(enterButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    /** 校验昵称，再启动后台线程连接服务器，避免等待网络时界面卡住。 */
    private void login() {
        String nickname = getNickname();
        if (nickname.isBlank()) {
            JOptionPane.showMessageDialog(this, "请输入昵称！");
            nicknameField.requestFocusInWindow();
            return;
        }

        // 防止连续点击或按回车，建立多个连接。
        enterButton.setEnabled(false);
        nicknameField.setEnabled(false);
        new Thread(() -> connectAndLogin(nickname), "client-login").start();
    }

    /** 建立连接并发送登录编号和昵称，成功后把连接交给聊天窗口。 */
    private void connectAndLogin(String nickname) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(Constant.SERVER_IP, Constant.SERVER_PORT), 5000);
            // 与服务端的 readInt()、readUTF() 顺序一一对应。
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(Constant.LOGIN_MESSAGE);
            dos.writeUTF(nickname);
            dos.flush();
            // 这里不能关闭 dos，否则也会关闭 Socket，后续聊天还要使用它。

            SwingUtilities.invokeLater(() -> {
                // 如果等待连接时取消了登录，就关闭刚建立的连接。
                if (!isDisplayable()) {
                    closeSocket(socket);
                    return;
                }
                new ClientChatFrame(nickname, socket);
                dispose();
            });
        } catch (IOException e) {
            closeSocket(socket);
            SwingUtilities.invokeLater(() -> {
                if (!isDisplayable()) {
                    return;
                }
                enterButton.setEnabled(true);
                nicknameField.setEnabled(true);
                JOptionPane.showMessageDialog(this,
                        "连接失败，请确认服务端已启动，IP 和端口正确。\n" + e.getMessage(),
                        "登录失败", JOptionPane.ERROR_MESSAGE);
                nicknameField.requestFocusInWindow();
            });
        }
    }

    /** 登录失败或取消时，释放本次创建的连接。 */
    private void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 统一按钮的尺寸、楷体字体和白色文字。 */
    private JButton createButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setFont(new Font("楷体", Font.BOLD, 22));
        button.setPreferredSize(new Dimension(104, 44));
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }

    /** 取得输入的昵称，去掉首尾空白，供后续登录逻辑使用。 */
    public String getNickname() {
        return nicknameField.getText().trim();
    }

    /** 取得进入按钮，便于外部代码绑定点击事件。 */
    public JButton getEnterButton() {
        return enterButton;
    }

    /** 在 Swing 事件线程中启动界面，直接运行本类即可预览。 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatEntryFrame::new);
    }
}
