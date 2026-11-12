package com.ranan.demo1create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadDemo3 {
    public static void main(String[] args){
        // 掌握多线程创建方式三：实现Callable接口，优势：可获取结果
        Callable<String> c1 = new MyCallable(100);
        // 把Callable对象封装成一个真正的线程任务对象FutureTask对象
        // FutureTask本质是一个Runnable线程任务对象，可以交给Thread对象处理
        FutureTask<String> f1 = new FutureTask<>(c1);
        Thread t1 = new Thread(f1);
        t1.start();

        Callable<String> c2 = new MyCallable(50);
        FutureTask<String> f2 = new FutureTask<>(c2);
        Thread t2 = new Thread(f2);
        t2.start();

        // 获取线程执行完毕的结果
        try {
            System.out.println(f1.get()); // 主线程会等第一个线程执行完才能往下面执行
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(f2.get()); // 主线程会等第二个线程执行完才能往下面执行
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<String> {
    private int n;
    public MyCallable(int n){
        this.n = n;
    }
    @Override
    public String call() throws Exception {
        int sum = 0;
        for(int i = 1;i <= n;++i){
            sum += i;
        }
        return "子线程计算结果：" + n + " : " + sum;
    }
}