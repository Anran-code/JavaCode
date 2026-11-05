package com.ranan.event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 {
    public static void main(String[] args) {
        // 第一种写法：提供实现类对象代表监听事件
        JFrame jf = new JFrame("登录窗口");
        jf.setSize(400,300);
        jf.setLocationRelativeTo(null); // 居中
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭的默认操作

        JPanel panel = new JPanel();
        jf.add(panel);

        JButton jb = new JButton("登录");
        panel.add(jb);

        // 实现类
        jb.addActionListener(new MyActionListener(jf));
        // 第二种写法就是匿名内部类
//        jb.addActionListener( e -> {
//            System.out.println("login");
//            JOptionPane.showMessageDialog(jf,"login!");
//        });

        jf.setVisible(true);
    }
}

class MyActionListener implements ActionListener {
    private JFrame jf;

    public MyActionListener(JFrame jf) {
        this.jf = jf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("login");
        JOptionPane.showMessageDialog(jf,"login!");
    }
}
