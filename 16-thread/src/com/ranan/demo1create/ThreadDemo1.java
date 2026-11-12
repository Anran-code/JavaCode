package com.ranan.demo1create;

public class ThreadDemo1 {
    public static void main(String[] args) { // main方法是主线程推动跑
        // 认识多线程，掌握创建线程的方式一：继承Thread类来实现
        // 1.定义一个子类，继承Thread类，成为一个线程类
        Thread thread = new MyThread1();
        // 4.启动线程
        thread.start(); // run()是不行的，没有创建线程
        // 主线程
        for(int i = 0;i < 10;++i){
            System.out.println("main线程输出 ：" + i);
        }
    }
}

class MyThread1 extends Thread {
    // 2.重写run方法
    @Override
    public void run() {
        // 3.线程任务代码
        for(int i = 0;i < 10;++i){
            System.out.println("子线程输出 ：" + i);
        }
    }
}
