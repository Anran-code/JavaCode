package com.ranan.demo7executorService;

import java.util.concurrent.*;

public class ExecutorServiceDemo1 {
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

        // 2.用线程池处理Runnable任务，看看会不会复用线程
        Runnable target = new MyRunnable();
        pool.execute(target); // 提交第一个任务 创建线程 自动启动 -> 核心线程
        pool.execute(target); // 提交第二个任务 -> 核心线程
        pool.execute(target); // 提交第三个任务 -> 核心线程
        pool.execute(target); // 提交第四个任务 复用线程 -> 一开始在任务队列，一旦有核心线程空闲就复用
        pool.execute(target); // 提交第五个任务 复用线程 -> 一开始在任务队列，一旦有核心线程空闲就复用
        pool.execute(target);
        pool.execute(target); // 到这时核心线程都在忙，任务队列也满了，需要创建临时线程
        pool.execute(target); // 到这时核心线程都在忙，任务队列也满了，需要创建临时线程
        pool.execute(target); // 抛异常，拒绝新任务，核心线程和临时线程和任务队列都满了

        // 创建临时线程：核心线程在忙，任务队列满，并且可以创建临时线程
        // 拒绝新任务：核心线程和临时线程都在忙，任务队列也满了，新的任务过来才能拒绝
        // 类比：核心线程：正在屋内工作的人  任务队列：屋外给多的人的座位  临时线程创建：屋内满了，座位上坐的人也满了，需要加临时工  拒绝任务：临时工也招满了，不让人再来了

        // 3.关闭线程池：一般不关闭
        pool.shutdown();
    }
}
