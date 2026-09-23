package com.ranan.obj.practice;
import java.util.Scanner;

// 随机生成一个1-100之间的数据，提示用户猜测，猜小提示过小，猜大提示过大
public class GuessNumber {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = getRandomNum();
        System.out.println("=========猜数字游戏(1-100)=========");
        System.out.println("你猜了共 " + guessNum(sc,num) + " 轮");
        System.out.println("=================================");
    }

    public static int getRandomNum(){
        return (int)(Math.random() * 100) + 1;
    }

    public static int guessNum(Scanner sc,int num){
        int round = 0;
        while(true){
            round++;
            System.out.printf("请输入猜测的数字：");
            int _num;
            if(sc.hasNextInt()){
                _num = sc.nextInt();
                if(_num == num){
                    System.out.println("你猜对了！答案是 " + num);
                    return round;
                } else if(_num >= 1 && _num < num){
                    System.out.println("你猜小了");
                } else if(_num > num && _num <= 100){
                    System.out.println("你猜大了");
                } else {
                    System.out.println("输入不合法！");
                }
            } else {
                sc.next();
                System.out.println("输入不合法！");
            }
        }
    }
}
