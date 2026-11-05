package com.ranan.stringdemo;

import java.util.Scanner;

public class StringDemo1 {
    public static void main(String[] args) {
        // 目标：掌握创建字符串对象，封装要处理的字符串数据，调用String提供的方法处理字符串
        // 1.直接创建字符串对象
        String s1 = "Hello World";
        System.out.println(s1);
        System.out.println(s1.length());

        // 2.构造器初始化对象
        String s2 = new String("Hello World");
        System.out.println(s2);

        // 3.字符数组创建
        char[] chars = {'H','e','l','l','o',' ','W','o','r','l','d'};
        String s3 = new String(chars);
        System.out.println(s3);

        // 4.字节数组创建
        byte[] bytes = {50,60,70,80,90};
        String s4 = new String(bytes);
        System.out.println(s4);
        
        // 只要是以"..."创建的字符串对象，会存储到字符串常量池，且相同的字符串只存储一份
        // 通过new方式创建字符串对象，每new一次都会产生一个新的对象放在堆内存中
        System.out.println("====================================");

        String t1 = "abc";
        String t2 = "abc";
        System.out.println(t1 == t2); // true

        String t3 = new String("abc");
        String t4 = new String("abc");
        System.out.println(t3 == t4); // false

        // 调用字符串方法（举例子）
        System.out.println("====================================");

        String loginName = "admin";
        Scanner sc = new Scanner(System.in);
        System.out.printf("请输入用户名：");
        String name = sc.next();

        if(loginName.equals(name))
            System.out.println("登录成功");
        else
            System.out.println("登陆失败");

        System.out.println("====================================");
        String number = "15581572906";
        String privateNumber = number.substring(0,3) + "****" + number.substring(7);
        System.out.println(privateNumber);
    }
}
