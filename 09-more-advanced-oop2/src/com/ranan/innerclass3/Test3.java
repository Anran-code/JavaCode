package com.ranan.innerclass3;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test3 {
    public static void main(String[] args) {
        // 匿名内部类使用场景
        // 需求：创建一个登录窗口，窗口上只有一个登录按钮
        JFrame win = new JFrame("登录窗口");

        // 画布（自适应大小）
        JPanel panel = new JPanel();
        win.add(panel);

        // 按钮
        JButton btn = new JButton("登录");
        panel.add(btn);

        // java要求必须给按钮添加一个点击事件监听器对象 （ActionListener的实现类对象）
        // 这里使用内部匿名类的方式来写，ActionListener是接口，用这种方法创建一个实现类及其对象
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮按下");
            }
        });

        win.setSize(300,200);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}
