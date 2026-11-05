package com.ranan.stream;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo4 {
    public static void main(String[] args) {
        // 学习stream流的统计、收集操作
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Anran",20,100000));
        teachers.add(new Teacher("Bobo",27,50000));
        teachers.add(new Teacher("Candy",33,4000));
        teachers.add(new Teacher("Dandy",21,9000));

        System.out.println("=================================================");

        teachers.stream().filter(t -> t.getSalary() > 10000).forEach(System.out::println);

        // 统计个数
        long count = teachers.stream().filter(t -> t.getAge() < 25).count();
        System.out.println(count);

        System.out.println("=================================================");

        // 找最大值
        Optional<Teacher> max = teachers.stream().max((t1,t2) -> Double.compare(t1.getSalary(),t2.getSalary()));
        System.out.println(max);
        System.out.println(max.get());

        System.out.println("=================================================");

        // 收集数据
        Stream<Teacher> s1 = teachers.stream().filter(t -> t.getName().startsWith("A"));
        List<Teacher> newTeachers =  s1.collect(Collectors.toList());
        System.out.println(newTeachers);

        // Set<Teacher> set = s1.collect(Collectors.toSet()); 异常：流不能收集两次
        Stream<Teacher> s2 = teachers.stream().filter(t -> t.getName().startsWith("A"));
        Set<Teacher> set = s2.collect(Collectors.toSet());
        System.out.println(set);

        Stream<Teacher> s3 = teachers.stream().filter(t -> t.getName().startsWith("A"));
        Object[] arr = s3.toArray();
        System.out.println(Arrays.toString(arr));

        System.out.println("=================================================");

        // 收集为map
        Stream<Teacher> s4 = teachers.stream().sorted((t1,t2) -> Double.compare(t1.getSalary(),t2.getSalary()));
        Map<String,Double> map = s4.collect(Collectors.toMap(Teacher::getName,Teacher::getSalary));
//      等价于：（匿名内部类的简写 -> 方法引用）
//        Map<String,Double> map = s4.collect(Collectors.toMap(new Function<Teacher, String>() {
//            @Override // 收集键
//            public String apply(Teacher teacher) {
//                return teacher.getName();
//            }
//
//        }, new Function<Teacher, Double>() {
//            @Override // 收集值
//            public Double apply(Teacher teacher) {
//                return teacher.getSalary();
//            }
//        }));

        System.out.println(map);
    }
}
