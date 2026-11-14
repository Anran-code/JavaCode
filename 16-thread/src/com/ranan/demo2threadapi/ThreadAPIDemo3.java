package com.ranan.demo2threadapi;

public class ThreadAPIDemo3 {
    public static void main(String[] args) {
        // t1.join()：让执行这句代码的当前线程（这里是主线程）等待 t1 结束
        MyThread2 t1 = new MyThread2();
        t1.start();

        for(int i = 1;i <= 5; ++i){
            System.out.println(Thread.currentThread().getName() + "线程输出：" + i); // main线程
            if(i == 1) {
                try {
                    t1.join(); // 主线程输出 1 后等待 t1 结束，再输出 2～5；t1 已结束则直接继续
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
