package com.ranan.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapDemo1 {
    public static void main(String[] args) {
        // 目标：学习map集合体系
        // 1.创建集合
        // Map<String,Integer> map = new HashMap<>(); // 无序，不重复，无索引
        // Map<String,Integer> map = new TreeMap<>(); // 按照大小默认升序排序、不重复、无索引
        Map<String,Integer> map = new LinkedHashMap<>(); // 有序，不重复，无索引
        map.put("Anran",20);
        map.put("Bobo",21);
        map.put("Anran",25);
        map.put("LiHua",21);
        map.put(null,null);
        System.out.println(map); // {null=null, Anran=25, Bobo=21, LiHua=21}
    }
}
