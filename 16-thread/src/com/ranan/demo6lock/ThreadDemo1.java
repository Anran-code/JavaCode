package com.ranan.demo6lock;

public class ThreadDemo1 {
    public static void main(String[] args) {
        // 学习线程同步方法二：同步方法
        // 1.设计一个账户类：用于创建小明和小红的共同账户类，存入十万
        Account acc = new Account("ICBC-110",100000);

        // 2.设计线程类：创建小明小红两个进程，模拟同一时间取款十万
        new DrawThread("小明",acc).start();
        new DrawThread("小红",acc).start();

        //小红取钱成功，取了：100000.0元
        //小红的余额为：0.0
        //小明取钱成功，取了：100000.0元
        //小明的余额为：-100000.0      // 引发了线程安全问题
    }
}

