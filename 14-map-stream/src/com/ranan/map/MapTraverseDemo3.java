package com.ranan.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class MapTraverseDemo3 {
    public static void main(String[] args) {
        // 学习Map的遍历方式
        Map<String,Integer> map = new HashMap<>(); // 有序，不重复，无索引
        map.put("Anran",20);
        map.put("Bobo",21);
        map.put("Anran",25);
        map.put("LiHua",21);
        System.out.println(map);

        System.out.println("=================================");

        // 1.提起Map集合的所有键到一个Set集合里面去
        Set<String> keys = map.keySet();
        for(String key:keys){
            System.out.println(map.get((key)));
        }

        System.out.println("=================================");

        // 2.获取键值对集合EntrySet()，键值对遍历
        Set<Map.Entry<String,Integer>> entries = map.entrySet();
        for(Map.Entry<String,Integer> entry:entries){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("=================================");

        // 3.lambda表达式遍历
        map.forEach(new BiConsumer<String, Integer>() { // BiConsumer接口的实现类
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + " : " + integer);
            }
        });

        System.out.println("=================================");

        // 简化
        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
