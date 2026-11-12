package com.ranan.demo2threadapi;

public class ThreadAPIDemo3 {
    public static void main(String[] args) {
        // 线程的join方法：线程插队：让 调用此方法的线程先执行
        MyThread2 t1 = new MyThread2();
        t1.start();

        for(int i = 1;i <= 5; ++i){
            System.out.println(Thread.currentThread().getName() + "线程输出：" + i); // main线程
            if(i == 1) {
                try {
                    t1.join(); // main线程输出：1 被打断
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        for(int i = 0;i < 10;++i){
            System.out.println(getName() + "线程输出：" + i);
        }
    }
}