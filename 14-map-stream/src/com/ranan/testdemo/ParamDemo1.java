package com.ranan.testdemo;

import java.util.Arrays;

public class ParamDemo1 {
    public static void main(String[] args) {
        // 学习可变参数
        sum();
        sum(1,2,3);
        sum(new int[]{1,2,3,4});
    }

    public static void sum(int...num){
        // 可变参数对内是一个数组
        System.out.println(num.length);
        System.out.println(Arrays.toString(num));
        System.out.println("================================");
    }
}
