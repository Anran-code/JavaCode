package com.ranan.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionDemo1 {
    public static void main(String[] args) {
        // 学习集合的有关知识
        // 1.list家族的集合：有序、可重复、有索引
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Java");
        list.add("C++");
        list.add("Python");
        System.out.println(list); // [Java, Java, C++, Python]
        System.out.println(list.getFirst());

        // 2.set家族的集合：无序、不可重复、无索引
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Java");
        set.add("C++");
        set.add("Python");
        System.out.println(set); // [Java, C++, Python]
    }
}

