package com.ranan.arraylistdemo;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        // 掌握ArrayList集合的基本使用
        ArrayList<String> list = new ArrayList<>(); // 泛型定义集合
        // 添加数据
        list.add("java");
        list.add("java2");
        list.add("安染");
        list.add("Anran");
        System.out.println(list);
        System.out.println("===============================");
        // 查看数据
        for(int i = 0;i < list.size();++i) {
            System.out.println(list.get(i));
        }
        System.out.println("===============================");
        // 删除数据
        System.out.println(list.remove(2));
        System.out.println(list);
        System.out.println("===============================");
        // 修改数据
        list.set(0,"java3");
        System.out.println(list);
    }
}
