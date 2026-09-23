package com.ranan.obj.branch;

import java.util.Scanner;

public class IfBranch {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        getBodyStatus(getBMI(sc));
    }

    public static double getBMI(Scanner sc){
        double bmi;
        while(true){
            System.out.printf("请输入你的BMI：");
            if(sc.hasNextDouble()){
                bmi = sc.nextDouble();
                if(bmi > 0) break;
            } else {
                sc.next();
            }
            System.out.printf("输入不合法！\n");
        }
        return bmi;
    }

    public static void getBodyStatus(double bmi){
        if(bmi < 18.5){
            System.out.println("你的体重不足");
        } else if(bmi >= 18.5 && bmi < 23.9){
            System.out.println("你的体重正常");
        } else if(bmi >= 24.0 && bmi < 27.9){
            System.out.println("你的体重超重");
        } else {
            System.out.println("你的体重肥胖");
        }
    }
}
