package com.ranan.demo2threadapi;

public class ThreadAPIDemo1 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("1号线程");
        t1.start();
        System.out.println(t1.getName());

        Thread t2 = new MyThread();
        t2.setName("2号线程");
        t2.start();
        System.out.println(t2.getName());

        // 哪个线程调用这个代码，这个代码就拿到哪个线程
        Thread m = Thread.currentThread(); // 主线程
        m.setName("主线程");
        System.out.println(m.getName());
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for(int i = 0;i < 10;++i){
            System.out.println(getName() + i);
        }
    }
}