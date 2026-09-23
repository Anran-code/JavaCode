package com.ranan.obj.basic;

public class MethodOverload {
    public static void main(String[] args){
        printVariable(12);
        printVariable("qwe");
    }

    public static void printVariable(int num){
        System.out.println(num);
    }

    public static void printVariable(String str){
        System.out.println(str);
    }
}
