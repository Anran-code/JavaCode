package com.ranan.demo1create;

public class ThreadDemo2 {
    public static void main(String[] args) {
        // 掌握多线程的创建方法二：实现Runnable接口来创建
        Runnable r = new MyRunnable();
        // 把任务对象包装成线程对象
        Thread t1 = new Thread(r);
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0;i < 10;++i){
//                    System.out.println("子线程输出 ：" + i);
//                }
//            }
//        });
        t1.start();
        // 主线程
        for(int i = 0;i < 10;++i){
            System.out.println("main线程输出 ：" + i);
        }
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for(int i = 0;i < 10;++i){
            System.out.println("子线程输出 ：" + i);
        }
    }
}