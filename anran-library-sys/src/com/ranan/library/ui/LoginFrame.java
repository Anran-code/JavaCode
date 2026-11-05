package com.ranan.library.ui;

import com.ranan.library.bean.Admin;
import com.ranan.library.bean.LoginUser;
import com.ranan.library.bean.User;
import com.ranan.library.data.LibraryData;

import javax.swing.*;
import java.awt.*;

/** 管理员与普通用户共用的登录界面。 */
public class LoginFrame extends JFrame {
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    /** 创建并显示登录窗口。 */
    public LoginFrame() {
        setTitle("登录 - 图书管理系统");
        setSize(640, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        init();
        setVisible(true);
    }

    /** 组装标题、输入区域和按钮区域。 */
    private void init() {
        Font formFont = new Font("宋体", Font.PLAIN, 18);
        add(createHeader(), BorderLayout.NORTH);
        add(createCenterPanel(formFont), BorderLayout.CENTER);
        add(createButtonPanel(formFont), BorderLayout.SOUTH);
    }

    /** 创建窗口上方的标题和说明文字。 */
    private JPanel createHeader() {
        JPanel header = new JPanel(new GridLayout(2, 1, 0, 6));
        header.setBorder(BorderFactory.createEmptyBorder(32, 0, 12, 0));

        JLabel titleLabel = new JLabel("图书管理系统", SwingConstants.CENTER);
        titleLabel.setFont(new Font("宋体", Font.BOLD, 28));
        JLabel subtitleLabel = new JLabel("管理员与普通用户共用登录入口", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        subtitleLabel.setForeground(Color.GRAY);

        header.add(titleLabel);
        header.add(subtitleLabel);
        return header;
    }

    /** 将两行输入表单整体放在窗口中央。 */
    private JPanel createCenterPanel(Font formFont) {
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 34));
        JPanel formPanel = new JPanel(new GridLayout(2, 1, 0, 18));

        nameField = new JTextField(18);
        passwordField = new JPasswordField(18);
        formPanel.add(createInputRow("用户名：", nameField, formFont));
        formPanel.add(createInputRow("密码：", passwordField, formFont));

        centerPanel.add(formPanel);
        return centerPanel;
    }

    /** 创建一行右对齐标签和输入框。 */
    private JPanel createInputRow(String labelText, JTextField field, Font formFont) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel label = new JLabel(labelText, SwingConstants.RIGHT);
        label.setFont(formFont);
        label.setPreferredSize(new Dimension(90, 42));
        field.setFont(formFont);
        field.setPreferredSize(new Dimension(250, 42));
        row.add(label);
        row.add(field);
        return row;
    }

    /** 创建登录和注册按钮，并绑定点击事件。 */
    private JPanel createButtonPanel(Font formFont) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 22, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 42, 0));

        loginButton = new JButton("登录");
        registerButton = new JButton("注册");
        for (JButton button : new JButton[]{loginButton, registerButton}) {
            button.setFont(formFont);
            button.setPreferredSize(new Dimension(120, 42));
            buttonPanel.add(button);
        }
        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());
        return buttonPanel;
    }

    /** 校验账号和密码，并识别登录身份。 */
    private void handleLogin() {
        String name = nameField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码都不能为空", "登录失败", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LoginUser account = LibraryData.findAccount(name);
        if (account == null || !account.getKeywords().equals(password)) {
            JOptionPane.showMessageDialog(this, "用户名或密码错误", "登录失败", JOptionPane.WARNING_MESSAGE);
            passwordField.setText("");
            return;
        }

        if (account instanceof User) {
            new UserLibrarySystemFrame((User) account);
            dispose();
        } else if (account instanceof Admin) {
            new AdminLibrarySystemFrame();
            dispose();
        }
    }

    /** 校验新账号，并加入登录账号列表。 */
    private void handleRegister() {
        String name = nameField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码都不能为空", "注册失败", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (LibraryData.findAccount(name) != null) {
            JOptionPane.showMessageDialog(this, "用户名已存在", "注册失败", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LibraryData.registerUser(name, password);
        passwordField.setText("");
        JOptionPane.showMessageDialog(this, "注册成功，请重新输入密码登录");
    }
}
