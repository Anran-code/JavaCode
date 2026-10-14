package com.ranan.interface4;

public interface A {
    // 1.默认方法（普通实例方法，区别于抽象方法）（默认public）
    // 使用接口的实现类来调用
    default void go(){
        System.out.println("go");
        run();
    }

    // 2.私有方法（JDK9）
    // 私有的实例方法，只能在接口内部的其他实例方法调用
    private void run(){
        System.out.println("run");
    }

    // 3.静态对象（默认public）
    // 只能使用当前接口调用！！！实现类不可以调用
    static void show(){
        System.out.println("show");
    }
}
