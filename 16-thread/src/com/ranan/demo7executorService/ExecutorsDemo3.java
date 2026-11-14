package com.ranan.demo7executorService;

import java.util.concurrent.*;

public class ExecutorsDemo3 {
    public static void main(String[] args) {
        // 通过线程池工具类：Executors，调用其静态方法直接得到线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
//        public static ExecutorService newFixedThreadPool(int nThreads) {
//            return new ThreadPoolExecutor(nThreads, nThreads,
//                    0L, TimeUnit.MILLISECONDS,
//                    new LinkedBlockingQueue<Runnable>());
//        }

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
