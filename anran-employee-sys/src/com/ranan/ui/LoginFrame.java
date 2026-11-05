package com.ranan.ui;

import com.ranan.bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField loginNameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    // 定义用户集合
    private static ArrayList<User> allUsers = new ArrayList<>();

    static {
        allUsers.add(new User("Admin1","Yzm20060714","Anran"));
        allUsers.add(new User("Admin2","12345678","Lihua"));
        allUsers.add(new User("李明","11111111","Liming"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 注册与登录分支
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == registerButton) {
            handleRegister();
        }
    }

    /** 用输入的登录名和密码查找用户；成功后进入员工管理界面。 */
    private void handleLogin() {
        String loginName = loginNameField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (loginName.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入登录名和密码");
            return;
        }

        User user = findUserByLoginName(loginName);
        if (user == null || !user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(this, "登录名或密码错误");
            passwordField.setText("");
            return;
        }

        JOptionPane.showMessageDialog(this, "欢迎，" + user.getLoginName());
        new EmployeeSystemFrame(loginName);
        this.dispose();
    }

    /** 检查登录名是否重复，并把新用户加入当前内存中的用户集合。 */
    private void handleRegister() {
        String loginName = loginNameField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (loginName.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入登录名和密码");
            return;
        }
        if (findUserByLoginName(loginName) != null) {
            JOptionPane.showMessageDialog(this, "登录名已存在");
            return;
        }

        // 当前界面没有单独填写用户名，暂时让它与登录名相同。
        allUsers.add(new User(loginName, password, loginName));
        JOptionPane.showMessageDialog(this, "注册成功，请点击登录");
        passwordField.setText("");
    }

    /** 根据登录名查找用户，找不到时返回 null。 */
    private User findUserByLoginName(String loginName) {
        for (User user : allUsers) {
            if (user.getLoginName().equals(loginName)) {
                return user;
            }
        }
        return null;
    }

    /** 创建登录窗口，设置窗口属性并显示界面。 */
    public LoginFrame() {
        this.setTitle("登录界面");
        this.setSize(600,450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        init();
        this.setVisible(true);
    }

    /** 将标题、表单区和按钮区组装到窗口中。 */
    private void init() {
        Font formFont = new Font("宋体", Font.PLAIN, 18);
        this.add(createTitleLabel(), BorderLayout.NORTH);
        this.add(createCenterPanel(formFont), BorderLayout.CENTER);
        this.add(createButtonPanel(formFont), BorderLayout.SOUTH);
    }

    /** 创建窗口顶部的系统标题标签。 */
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("人事管理系统");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 15, 0));
        return titleLabel;
    }

    /** 创建中央容器，让表单整体水平居中并与标题留出距离。 */
    private JPanel createCenterPanel(Font formFont) {
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        centerPanel.add(createFormPanel(formFont));
        return centerPanel;
    }

    /** 创建上下两行的表单，并初始化登录名和密码输入框。 */
    private JPanel createFormPanel(Font formFont) {
        JPanel formPanel = new JPanel(new GridLayout(0, 1, 0, 15));
        loginNameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        formPanel.add(createInputRow("登录名：", loginNameField, formFont));
        formPanel.add(createInputRow("密码：", passwordField, formFont));
        return formPanel;
    }

    /**
     * 创建一行“提示文字 + 输入框”；JPasswordField 也能作为 JTextField 参数传入。
     * @param labelText 左侧提示文字
     * @param inputField 右侧输入框
     * @param formFont 标签和输入框共用的字体
     * @return 排好这一行组件的面板
     */
    private JPanel createInputRow(String labelText, JTextField inputField, Font formFont) {
        JLabel inputLabel = new JLabel(labelText);
        inputLabel.setFont(formFont);
        inputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        inputLabel.setPreferredSize(new Dimension(90, 40));
        inputField.setFont(formFont);
        inputField.setPreferredSize(new Dimension(200, 40));

        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        row.add(inputLabel);
        row.add(inputField);
        return row;
    }

    /** 创建窗口底部的按钮区，并初始化登录、注册按钮。 */
    private JPanel createButtonPanel(Font formFont) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 35, 0));
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");
        loginButton.setFont(formFont);
        registerButton.setFont(formFont);
        loginButton.setPreferredSize(new Dimension(110, 40));
        registerButton.setPreferredSize(new Dimension(110, 40));

        // 登录预注册按钮监听
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        return buttonPanel;
    }
}
