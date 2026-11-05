package com.ranan.map;

import java.util.Map;
import java.util.TreeMap;

public class MapDemo4 {
    public static void main(String[] args) {
        // 学习自定义排序TreeMap
        // 1.重写compareTo方法
        Map<Teacher,String> map = new TreeMap<>();
        map.put(new Teacher("张三",23,10000),"北京");
        map.put(new Teacher("李四",21,12000),"北京");
        map.put(new Teacher("王五",33,20000),"长沙");
        map.put(new Teacher("艾六",29,10500),"上海");
        System.out.println(map);

        // 2.lambda表达式
        Map<Teacher,String> map1 = new TreeMap<>((o1,o2) -> Integer.compare(o1.getAge(),o2.getAge()));
        map.put(new Teacher("张三",23,10000),"北京");
        map.put(new Teacher("李四",21,12000),"北京");
        map.put(new Teacher("王五",33,20000),"长沙");
        map.put(new Teacher("艾六",29,10500),"上海");
        System.out.println(map);
    }
}
