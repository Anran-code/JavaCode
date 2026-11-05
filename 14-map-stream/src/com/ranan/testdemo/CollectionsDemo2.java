package com.ranan.testdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 批量添加元素
        Collections.addAll(list,"Anran","Bobo","Candy");
        System.out.println(list);

        // 打乱顺序
        Collections.shuffle(list);
        System.out.println(list);

        // sort方法
    }
}
