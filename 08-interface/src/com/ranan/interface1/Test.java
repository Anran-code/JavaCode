package com.ranan.interface1;

public class Test {
    public static void main(String[] args) {
        System.out.println(A.SCHOOL_NAME);
        // 接口是被类来实现的
        C c = new C();
        c.play();
    }
}

// C为实现类，同时实现多个接口
// 必须实现所有方法，否则要定义为抽象类
class C implements A,B{
    @Override
    public void run() {
        System.out.println("run");
    }

    @Override
    public void run1() {
        System.out.println("run1");
    }

    @Override
    public void play() {
        System.out.println("play");
    }
}