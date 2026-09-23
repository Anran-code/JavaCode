package com.ranan.obj.type;

public class Type {
    public static void main(String[] args){
        byte a = 12;
        print1(a); // 自动类型转换
        print2(a); // 自动类型转换

        System.out.println(cal1(1,2,3.2,'a'));
        // 但是大类型不能自动转小类型，需要强制类型转换
        // byte -> short -> int -> long
    }

    public static void print1(int b){
        System.out.println(b);
    }

    public static void print2(double b){
        System.out.println(b);
    }

    public static double cal1(int a, int b,double c,char r){
        return a + b + c + r;
    }
}
