package com.ranan.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionDemo2 {
    public static void main(String[] args) {
        // 目标：学习Collection的功能
        Collection<String> list = new ArrayList<>();

        // 1.添加元素
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);

        // 2.获取元素个数
        System.out.println(list.size());

        // 3.删除集合元素
        list.remove("c");
        System.out.println(list);

        // 4.清空集合
        list.clear();

        // 5.检测集合是否为空
        System.out.println(list.isEmpty());

        // 6.判断集合是否包含某元素
        System.out.println(list.contains("d"));

        // 7.集合转数组
        Object[] arr1 = list.toArray();
        System.out.println(Arrays.toString(arr1));

        // 8.转字符数组：构造器引用
        String[] arr2 = list.toArray(String[]::new);
        System.out.println(Arrays.toString(arr2));
    }
}
