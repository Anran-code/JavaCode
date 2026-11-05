package com.ranan.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo1 {
    public static void main(String[] args) {
        // 目标：认识Stream流，掌握其基本使用步骤，体会优势与特点
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.add("张翠山");

        // 1.传统方案：找出姓张，三个字的人，放入新集合
        List<String> newList = new ArrayList<>();
        for(String name : list){
            if(name.startsWith("张") && name.length() == 3)
                newList.add(name);
        }
        System.out.println(newList);

        // 2.使用stream流（链式调用）
        // 获取数据源（集合等）的stream流.stream()
        // 调用流水线方法.filter()
        // 收集结果.collect()
        List<String> newList2 =  list.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).collect(Collectors.toList());
        System.out.println(newList2);
    }
}

