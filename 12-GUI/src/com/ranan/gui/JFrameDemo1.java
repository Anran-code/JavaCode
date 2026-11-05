package com.ranan.gui;

import javax.swing.*;

public class JFrameDemo1 {
    public static void main(String[] args) {
        // 了解GUI Swing的编程
        // 1.创建一个窗口，有一个登录按钮
        JFrame jf = new JFrame("登录窗口");
        jf.setSize(400,300);
        jf.setLocationRelativeTo(null); // 居中
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭的默认操作

        JPanel panel = new JPanel();
        jf.add(panel);

        JButton jb = new JButton("登录");
        panel.add(jb);

        jf.setVisible(true);
    }
}
