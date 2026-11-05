package com.ranan.genericity;

import java.util.ArrayList;

public class GenericDemo1 {
    public static void main(String[] args) {
        // 目标：认识泛型，学习泛型好处
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(12.3);
        list.add("aaaa");
        list.add(true);
        list.add('a');
        list.add(new Object());

//        for(int i = 0;i < list.size();i++){
//            Object rs = list.get(i);
//            // 强转类型，会出错，所以需要统一类型
//            String str = (String) rs;
//            System.out.println(str);
//        }

        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(123);
        intList.add(124);
        for(int i = 0;i < intList.size();i++){
            String str = Integer.toString(intList.get(i));
            System.out.println(str);
        }
    }
}
