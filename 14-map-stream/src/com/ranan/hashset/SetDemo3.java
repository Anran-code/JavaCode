package com.ranan.hashset;

import java.util.Comparator;
import java.util.TreeSet;

public class SetDemo3 {
    public static void main(String[] args) {
        // 目标：学习TreeSet的对于自定义对象的排序
        // 方案二：
        TreeSet<Teacher> set = new TreeSet<>(new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                return Integer.compare(o1.getAge(),o2.getAge());
            }
        }); // TreeSet<Teacher> set = new TreeSet<>((o1,o2) -> Integer.compare(o1.getAge(),o2.getAge()));
        set.add(new Teacher("老陈",40,6000));
        set.add(new Teacher("老徐",42,8000));
        set.add(new Teacher("老张",33,10000));
        set.add(new Teacher("老李",40,5000));
        System.out.println(set); // TreeSet默认不能给自定义对象排序

        // 方案一：对象类实现一个Comparable比较接口，重写compareTo方法，实现逻辑
        // 方案二：public TreeSet (Comparator c)集合自带比较器Comparator对象，指定比较规则
    }
}
