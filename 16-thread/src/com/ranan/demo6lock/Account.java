package com.ranan.demo6lock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String cardId; // 卡号
    private double money; // 余额
    private final Lock lock = new ReentrantLock(); // final保护锁对象（把同一个示例对象绑定一把锁）

    public void drawMoney(double money){
        // 拿到当前谁来取钱
        String name = Thread.currentThread().getName();
        lock.lock(); // 上锁
        try {
            // 判断余额是否够
            if(this.money >= money){
                // 钱够
                System.out.println(name + "取钱成功，取了：" + money + "元");
                this.money -= money;
                System.out.println(name + "的余额为：" + this.money);
            } else {
                System.out.println(name + "余额不足");
            }
        } finally {
            lock.unlock();
        }
    }
}
