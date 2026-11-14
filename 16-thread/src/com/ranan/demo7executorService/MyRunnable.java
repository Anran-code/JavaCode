package com.ranan.demo7executorService;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for(int i = 0;i < 10;++i){
            System.out.println(Thread.currentThread().getName() + "线程输出 ：" + i);
        }
    }
}