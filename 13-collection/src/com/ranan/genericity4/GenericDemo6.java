package com.ranan.genericity4;

import java.util.ArrayList;

public class GenericDemo6 {
    public static void main(String[] args) {
        // 目标：泛型和集合不支持基本数据类型，只支持对象类型（引用数据类型），学习包装类（万物皆对象）
        // ArrayList<int> list = new ArrayList<int>(); 报错
        // int -> Integer 把基本数据类型包装成对象类型
        Integer it1 = Integer.valueOf(100);
        Integer it2 = Integer.valueOf(100);
        System.out.println(it1 == it2);

        // 自动装箱：基本数据类型可以直接变成包装对象的数据，不需要额外做任何事情
        Integer it3 = 100;

        // 自动拆箱：把包装类型的对象直接给基本类型的数据
        int i = it3;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(100); // 自动装箱
        int rs = list.get(0); // 自动拆箱

        // 包装类新增的功能：
        // 1.把基本类型的数据转换成字符串
        int j = 23;
        String rs1 = Integer.toString(j); // "23"
        System.out.println(rs1 + 1);      // 231
        // 2.把字符串类型转换成基本数据类型
        String str = "98";
        System.out.println(Integer.parseInt(str) + 1); // 99
    }
}
