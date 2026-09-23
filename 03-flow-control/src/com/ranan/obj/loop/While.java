package com.ranan.obj.loop;

public class While {
    public static void main(String[] args){
        getYears();
        getTimes();
    }

    // 计算 100000 多少年后能在复利 1.7% 下翻倍
    public static void getYears(){
        double money = 100000;
        double rate = 0.017;
        int years = 0;
        while(money < 200000){
            money += money * rate;
            years++;
        }
        System.out.println(years+"年");
    }

    // 珠峰是 8848860mm ，一张纸足够大，厚 0.1mm ，要叠多少次超过
    public static void getTimes(){
        int height = 8848860;
        int times = 0;
        double thickness = 0.1;
        while(thickness < height){
            times++;
            thickness *= 2;
        }
        System.out.println(times+"次");
    }
}
