package com.ranan.stream;

import java.util.*;
import java.util.stream.Stream;

public class StreamDemo2 {
    public static void main(String[] args) {
        // 获取stream流的方法
        // 1.获取集合的stream流，调用集合的stream方法
        Collection<String> list = new ArrayList<>();
        Stream<String> s1 = list.stream();

        // 2.Map获取Stream流
        Map<String,Integer> map = new HashMap<>();
        // 获取键流
        Stream<String> s2 = map.keySet().stream();
        // 获取值流
        Stream<Integer> s3 = map.values().stream();
        // 获取键值对流
        Stream<Map.Entry<String,Integer>> s4 = map.entrySet().stream();

        // 3.数组获取Stream流
        String[] arr = {"a","b","c"};
        Stream<String> s5 = Arrays.stream(arr);
        System.out.println(s5.count());

        Stream<String> s6 = Stream.of(arr);

        Stream<String> s7 = Stream.of("a","b","c");
    }
}
