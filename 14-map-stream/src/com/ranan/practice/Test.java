package com.ranan.practice;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        // 某班级80名学生，现在组织秋游活动，班长提供了四个景点（A,B,C,D），每个学生只能选择一个景点，统计出哪个景点想去的人最多
        calc();
    }

    public static void calc(){
        List<String> list = new ArrayList<>();
        String[] name = {"天安门","故宫","颐和园","天坛"};
        for(int i = 0;i < 80;++i){
            list.add(name[(int)(Math.random() * 4)]);
        }
        System.out.println(list);

        // map去记录次数
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0;i < 80;++i){
            if(!map.containsKey(list.get(i))){
                map.put(list.get(i),1);
            } else {
                map.put(list.get(i),map.get(list.get(i)) + 1);
            }
        }
        System.out.println(map);

        int max = 0;
        String maxName = null;
        for(Map.Entry<String,Integer> entry: map.entrySet()){
            if(entry.getValue() > max){
                maxName = entry.getKey();
                max = entry.getValue();
            }
        }
        System.out.println("最热门景点是：" + maxName + "，共 " + max + " 人");
    }
}
