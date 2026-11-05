package com.ranan.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class CollectionTravelDemo3 {
    public static void main(String[] args) {
        // 目标：掌握集合的迭代器遍历
        Collection<String> list = new ArrayList<>();

        list.add("Anran");
        list.add("Mama");
        list.add("Nikita");
        list.add("cyka");

        // 1.得到集合的迭代器对象，初始是指在第一个元素前
        Iterator<String> it = list.iterator();
//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println(it.next());

        // 循环迭代 `
        while(it.hasNext()){
            System.out.println(it.next());
        }

        // 增强for循环
        for(String s : list){
            System.out.println(s);
        }

        // Lambda表达式遍历
        // void forEach(Consumer<? super T> action) 匿名内部类
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        // Lambda表达式
        list.forEach(s -> System.out.println(s));

        // 方法引用
        list.forEach(System.out::println);
    }
}
