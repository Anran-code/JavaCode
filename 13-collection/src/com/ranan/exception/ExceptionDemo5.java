package com.ranan.exception;

import java.util.Scanner;

public class ExceptionDemo5 {
    public static void main(String[] args) {
        // 目标：掌握异常的吃力方案2：捕获异常对象，尝试重新修复
        // 接收用户的定价
        double price = 0;
        while(true){
            try {
                price = userInputPrice();
                System.out.println("设置了定价" + price);
                break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("不要瞎输入");
            }
        }
    }

    public static double userInputPrice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入定价:");
        double price = sc.nextDouble();
        return price;
    }
}
