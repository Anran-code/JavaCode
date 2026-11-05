package com.ranan.hashset;

import java.util.HashSet;

public class SetDemo2 {
    public static void main(String[] args) {
        // 目标：学习HashSet的去重操作
        HashSet<Student> set = new HashSet<>();
        set.add(new Student("张三",18,98));
        set.add(new Student("张三",18,98));
        set.add(new Student("李四",20,95));
        set.add(new Student("李四",20,95));

        System.out.println(set);
    }
}
