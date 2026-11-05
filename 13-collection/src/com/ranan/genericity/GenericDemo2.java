package com.ranan.genericity;

import java.util.ArrayList;

public class GenericDemo2 {
    public static void main(String[] args){
        // 目标：学习泛型类，用MyArrayList来学习
        MyArrayList<String> list = new MyArrayList<>();
        list.add("123");
        list.add("1234");
        list.remove("123");

        System.out.println(list);
    }
}
