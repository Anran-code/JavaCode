package com.ranan.demo7executorService;

import java.util.concurrent.*;

public class ExecutorServiceDemo2 {
    public static void main(String[] args) {
        // 目标：创建线程池对象来使用
        // 1.使用线程池的实现类ThreadPoolExecutor声明七个参数来实现创建线程池对象
        // 1) corePoolSize: 核心线程的数量
        // 2） maximumPoolSize: 最大线程数量
        // 3) keepAliveTime: 临时线程的存活时间
        // 4) unit: 时间单位（TimeUnit）
        // 5) workQueue: 线程池的任务队列
        // 6) threadFactory: 线程工厂
        // Executors.defaultThreadFactory()); 默认线程工厂
        // 7) handle: 线程池的任务拒绝策略
        ExecutorService pool = new ThreadPoolExecutor(3, 5,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // 2.用线程池处理Callable任务，看看会不会复用线程
        Future<String> f1 = pool.submit(new MyCallable(100));
        Future<String> f2 = pool.submit(new MyCallable(200));
        Future<String> f3 = pool.submit(new MyCallable(300));
        Future<String> f4 = pool.submit(new MyCallable(400)); // 复用

        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
            System.out.println(f4.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
