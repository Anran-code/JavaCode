package com.ranan.demo5synchronized_method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String cardId; // 卡号
    private double money; // 余额

    public synchronized void drawMoney(double money){
        // 拿到当前谁来取钱
        String name = Thread.currentThread().getName();
        // 判断余额是否够
        if(this.money >= money){
            // 钱够
            System.out.println(name + "取钱成功，取了：" + money + "元");
            this.money -= money;
            System.out.println(name + "的余额为：" + this.money);
        } else {
            System.out.println(name + "余额不足");
        }
    }
}
