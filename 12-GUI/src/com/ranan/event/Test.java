package com.ranan.event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Test {
    public static void main(String[] args) {
        // 认识GUI的事件处理
        JFrame jf = new JFrame("登录窗口");
        jf.setSize(400,300);
        jf.setLocationRelativeTo(null); // 居中
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭的默认操作

        JPanel panel = new JPanel();
        jf.add(panel);

        JButton jb = new JButton("登录");
        panel.add(jb);

        // 给按钮绑定事件监听器
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // e是事件对象，可以了解事件详情
                System.out.println(e.getID());
                JOptionPane.showMessageDialog(jf,"有人点击了登录");
            }
        });

        // 窗口处于活动状态时，即使按钮获得焦点，也能响应方向键
        bindArrowKey(jf.getRootPane(), KeyEvent.VK_UP, "up");
        bindArrowKey(jf.getRootPane(), KeyEvent.VK_DOWN, "down");
        bindArrowKey(jf.getRootPane(), KeyEvent.VK_RIGHT, "right");
        bindArrowKey(jf.getRootPane(), KeyEvent.VK_LEFT, "left");

        jf.setVisible(true);
    }

    private static void bindArrowKey(JRootPane rootPane, int keyCode, String direction) {
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(keyCode, 0), direction);
        rootPane.getActionMap().put(direction, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(direction);
            }
        });
    }
}
