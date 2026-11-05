package com.ranan.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 自定义窗口
public class LoginFrame extends JFrame implements ActionListener {
    public LoginFrame() {
        // 1.设置窗口标题
        this.setTitle("Login");
        // 2.设置窗口的大小
        this.setSize(400,300);
        // 3.设置窗口的位置
        this.setLocationRelativeTo(null);
        // 4.设置窗口关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"Login!");
    }

    private void init() {
        JPanel panel = new JPanel();
        JButton jb = new JButton("Login");

//        jb.addActionListener( e -> {
//            JOptionPane.showMessageDialog(this,"Login!");
//        });
        jb.addActionListener(this); // this既代表界面对象本身，也代表实现了的ActionListener监听器对象

        panel.add(jb);
        this.add(panel);
    }
}
