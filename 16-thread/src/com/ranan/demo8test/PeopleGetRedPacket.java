package com.ranan.demo8test;

import java.util.List;

public class PeopleGetRedPacket extends Thread{
    private List<Integer> redPacket;
    private int totalMoney = 0; // 当前员工抢到的红包总金额

    public PeopleGetRedPacket(List<Integer> redPacket,String name){
        super(name);
        this.redPacket = redPacket;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    @Override
    public void run() {
        String name = getName();
        while(true){ // while确保一个人能多次抢

            // 被锁的线程会停在这里

            // 100 个人来抢 redPacket里的钱，给红包加锁
            synchronized (redPacket){
                if(redPacket.size() == 0){
                    break; // 放到锁内确保所有线程都能停止（对于等锁的线程）
                }

                // 随机一个索引
                int index = (int)(Math.random() * redPacket.size());
                int money = redPacket.remove(index);
                totalMoney += money;
                System.out.println(name + "抢到了红包" + money + "元");

                if(redPacket.size() == 0)
                    System.out.println("活动结束");
            }

            // 抢到了停一会，防止老是一个人抢
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
