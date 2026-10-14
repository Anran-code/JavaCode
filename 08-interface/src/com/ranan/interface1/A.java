package com.ranan.interface1;

// 接口类不可以创建对象
public interface A {
    // 1.定义常量
    String SCHOOL_NAME = "TongJi University";
    // 2.抽象方法
    public abstract void run();
    // public abstract 可以省略
    void run1();
}
