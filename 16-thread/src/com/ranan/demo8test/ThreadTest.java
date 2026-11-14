package com.ranan.demo8test;

import java.util.*;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        // 多线程综合小案例：
        // 红包雨游戏：某企业有100名员工，员工的工号是1，2，3，4...100。现在公司举办了年会活动，一次性发出200个红包。
        // 其中小红包在[1-30]之间，占80%，大红包在[31-100]，占20%
        // 1.系统模拟上述要求产生200个红包
        // 2.模拟一百个员工抢红包，需要输出哪个员工抢哪个红包的过程，活动结束时需要提示结束
        // 3.活动结束后，对100名员工的红包总额降序显示

        // 分析：100个员工就是100个线程，来竞争200个红包
        List<Integer> redPacket = getRedPacket();
        List<PeopleGetRedPacket> people = new ArrayList<>();
        // 定义线程类，创建一百个线程，竞争同一个集合
        for(int i = 1;i <= 100;i++){
            PeopleGetRedPacket person = new PeopleGetRedPacket(redPacket, "员工" + i);
            people.add(person);
            person.start();
        }

        // 前面的循环已启动全部员工，下面的循环由主线程执行
        // 主线程等待某位员工时，其他已启动的员工仍会继续抢红包
        for (PeopleGetRedPacket person : people) {
            person.join(); // 主线程等这位员工结束；如果员工已结束，就直接继续
        }
        // 循环正常结束，说明所有员工都已结束，才能按最终累计金额排序

        // 按总金额降序排列，没抢到红包的员工也保留在排行榜中
        people.sort((a, b) -> Integer.compare(b.getTotalMoney(), a.getTotalMoney()));
        System.out.println("红包总额排行榜：");
        for (PeopleGetRedPacket person : people) {
            System.out.println(person.getName() + "：" + person.getTotalMoney() + "元");
        }
    }

    // 准备200个红包返回，放到List
    public static List<Integer> getRedPacket(){
        Random r = new Random();
        // 其中小红包在[1-30]之间，占80%，大红包在[31-100]，占20%
        List<Integer> redPacket = new ArrayList<>();
        for(int i = 1;i <= 160;++i){
            redPacket.add(r.nextInt(30) + 1); // [0-29] + 1
        }
        for(int i = 1;i <= 40;++i){
            redPacket.add(r.nextInt(70) + 31); // [0-69] + 31
        }
        return redPacket;
    }
}
