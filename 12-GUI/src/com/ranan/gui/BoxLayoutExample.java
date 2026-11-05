package com.ranan.gui;

import javax.swing.*;
import java.awt.*;

public class BoxLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BoxLayoutExample");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS)); // 设置布局管理器，Y垂直排列

        panel.add(new JButton("Button1"));
        panel.add(Box.createVerticalStrut(10)); // 设置垂直间隔
        panel.add(new JButton("Button2"));
        panel.add(Box.createVerticalStrut(10)); // 设置垂直间隔
        panel.add(new JButton("Button3"));
        panel.add(Box.createVerticalStrut(10)); // 设置垂直间隔
        panel.add(new JButton("Button4"));

        frame.add(panel);
        frame.setVisible(true);
    }
}
