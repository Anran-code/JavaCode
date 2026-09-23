package com.ranan.obj.array;
import java.util.Scanner;

// 静态类似于c++，主要学习动态创建数组
public class Array {
    public static void main(String[] args){
        double[] grades = new double[8];
        Scanner sc = new Scanner(System.in);
        getInput(grades,sc);
        getAverage(grades);
        getMax(grades);
    }

    public static void getInput(double[] grades,Scanner sc){
        for(int i = 0;i < grades.length;++i){
            while(true){
                System.out.printf("请输入第 %d 个人的成绩：",i + 1);
                if(sc.hasNextDouble()){
                    grades[i] = sc.nextDouble();
                    if(grades[i] >= 0) break;
                } else sc.next();
                System.out.println("输入不合法！");
            }
        }
    }

    public static void getAverage(double[] grades){
        double totalgrade = 0;
        for(int i = 0;i < grades.length;++i){
            totalgrade += grades[i];
        }
        System.out.printf("平均分是：%.2f%n",totalgrade / grades.length);
    }

    public static void getMax(double[] grades){
        double max = grades[0];
        for(int i = 1;i < grades.length;++i){
            max = Math.max(grades[i], max);
        }
        System.out.printf("最高分是：%f%n",max);
    }
}
