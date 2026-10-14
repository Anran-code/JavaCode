package com.ranan.myfinal;

public class FinalDemo {
    public static final String NAME = "FinalStaticVariable";
    public static void main(String[] args) {
        // 3.final修饰变量，变量有且仅能赋值一次
        final double PI = 3.14;
        // PI = 3.1415 (X)
        // final 记住的是地址，所以数组的值是允许修改的
        final int[] arr = {1,2,3};
        arr[2] = 5;
    }
}

// 1.final修饰类，类不可以被继承
final class A{}
//class B extends A{} 不可以继承final类

// 2.final修饰方法，方法不可以被重写
class C{
    public final void show(){
        System.out.println("show");
    }
}

class D extends C{
//    @Override
//    public void show(){
//
//    }
}