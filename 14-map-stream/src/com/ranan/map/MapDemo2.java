package com.ranan.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo2 {
    public static void main(String[] args) {
        // 目标：学习map集合API
        Map<String,Integer> map = new HashMap<>(); // 有序，不重复，无索引
        map.put("Anran",20);
        map.put("Bobo",21);
        map.put("Anran",25);
        map.put("LiHua",21);
        map.put(null,null);
        System.out.println(map); // {null=null, Anran=25, Bobo=21, LiHua=21}

        System.out.println("=================================");

        // 常用API
        // 1.根据键取值
        System.out.println(map.get("Anran")); // 25
        System.out.println(map.get("Anran1")); // null

        System.out.println("=================================");

        // 2.是否包含某个键
        System.out.println(map.containsKey("Anran")); // true
        System.out.println(map.containsKey("Anran1")); // false

        System.out.println("=================================");

        // 3.是否包含某个值
        System.out.println(map.containsValue(25)); // true
        System.out.println(map.containsValue(22)); // false

        System.out.println("=================================");

        // 4.删除元组
        System.out.println(map.remove("Anran"));
        System.out.println(map);

        System.out.println("=================================");

        // 5.清空map：map.clear();
        // 6.判断集合是否为空
        System.out.println(map.isEmpty());

        System.out.println("=================================");

        // 7.返回集合大小
        System.out.println(map.size());

        System.out.println("=================================");

        // 8.获取所有键的集合
        Set<String> set = map.keySet();
        System.out.println(set);

        System.out.println("=================================");

        // 9.获取所有值的集合
        Collection<Integer> collection = map.values();
        System.out.println(collection); // 有重复
    }
}
