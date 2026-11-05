package com.ranan.hashset;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo1 {
    public static void main(String[] args) {
        // 认识set家族集合特点
        // Set<String> set = new HashSet<>(); // 无序，不重复，无索引
        Set<String> set = new LinkedHashSet<>(); // 有序，不重复，无索引
        set.add("Anran");
        set.add("Anran");
        set.add("Bobo");
        set.add("Bobo");
        System.out.println(set);

        // TreeSet集合，排序，不重复，无索引
        Set<Integer> set1 = new TreeSet<>();
        set1.add(1);
        set1.add(1);
        set1.add(5);
        set1.add(2);
        set1.add(4);
        set1.add(8);
        System.out.println(set1);

        System.out.println("==========================");

        String s1 = "abc";
        String s2 = "def";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        // HashSet底层原理：数组+链表+红黑树（JDK8之后）
    }
}
