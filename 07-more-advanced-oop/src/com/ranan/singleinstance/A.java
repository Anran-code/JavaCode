package com.ranan.singleinstance;

public class A {
    // 2.定义一个静态变量，用于基本类的一个唯一对象
    private static A a = new A();

    // 1.私有化构造器
    private A(){
    }

    // 3.获取单例对象
    public static A getInstance(){
        return a;
    }
}
