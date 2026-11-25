package com.ranan.demo8api;

public class Test2 {
    public static void main(String[] args) {
        // 目标：高效字符串拼接
        // + 号拼接字符串内容，大量拼接效率极差
        // String的对象是不可变对象，共享数据性能还可以，修改性能极差！！！
//        String s = "";
//        for(int i = 0;i < 100000;++i){
//            s += "abc";
//        }
//        System.out.println(s);

        StringBuilder sb = new StringBuilder(); // 可变变量，适合修改
        for(int i = 0;i < 1000000;++i){
            sb.append("abc");
        }
        System.out.println(sb.toString());
    }
}
