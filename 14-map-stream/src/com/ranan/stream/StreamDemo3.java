package com.ranan.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo3 {
    public static void main(String[] args) {
        // 目标：学习Stream流的中间方法
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.add("张翠山");

        // 1.过滤方法
        list.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).forEach(System.out::println);

        System.out.println("=====================================");

        // 2.排序方法
        List<Double> scores = new ArrayList<>();
        scores.add(99.2);
        scores.add(12.4);
        scores.add(77.8);
        scores.add(91.1);
        scores.add(91.1);
        scores.stream().sorted().forEach(System.out::println); // 默认升序

        System.out.println("=====================================");

        scores.stream().sorted((s1,s2) -> Double.compare(s2,s1)).forEach(System.out::println); // 重写compare方法，自定义倒序

        System.out.println("=====================================");

        // 限制打印数量
        scores.stream().sorted((s1,s2) -> Double.compare(s2,s1)).limit(2).forEach(System.out::println); // 只取前两个

        System.out.println("=====================================");

        // 去重
        scores.stream().sorted((s1,s2) -> Double.compare(s2,s1)).distinct().forEach(System.out::println);

        System.out.println("=====================================");

        // 映射/加工方法，把流上的数据拿出来变成新数据又放到流上去
        scores.stream().map(s -> "加十分后：" + (s + 10)).forEach(System.out::println);

        // 合并流：
        Stream<String> s1 = Stream.of("Andy","DouBao","NaiWa");
        Stream<Integer> s2 = Stream.of(1,2,3);

        System.out.println("=====================================");
        Stream<Object> newList = Stream.concat(s1,s2);
        newList.forEach(System.out::println);
    }
}
