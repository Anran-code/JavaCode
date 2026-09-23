package com.ranan.obj.practice;
import java.util.Scanner;

// 开发一个简单的健康计算器应用程序，它可以接受用户的输入（年龄、性别、体重、身高），计算出用户的BMI和基础代谢率（BMR）

public class Practice {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in); // 只创建一个Scanner对象

        String userName = getName(sc);
        String userGender = getGender(sc);
        int userAge = getAge(sc);
        double userHeight = getHeight(sc);
        double userWeight = getWeight(sc);

        System.out.println("===========" + userName + "的身体数据===========");
        System.out.printf("性别: %s%n年龄: %d 岁%n身高: %.2f cm%n体重: %.2f kg%n",userGender,userAge,userHeight,userWeight);
        System.out.printf("BMI: %.2f kg/m^2%n",getBMI(userHeight,userWeight));
        System.out.printf("BMR: %.2f kcal/day%n",getBMR(userGender,userAge,userHeight,userWeight));

        sc.close();
    }

    public static String getName(Scanner sc){
        System.out.println("请输入姓名：");
        String name = sc.nextLine();
        return name;
    }

    public static int getAge(Scanner sc){
        int age;
        while(true){
            System.out.println("请输入年龄：");
            if(sc.hasNextInt()){
                age = sc.nextInt();
                if(age > 0) break;
            } else {
                sc.next();
            }
            System.out.println("输入不合法!");
        }
        return age;
    }

    public static String getGender(Scanner sc){
        while(true){
            System.out.println("您的性别是：1.男 2.女 （请输入1/2）");
            int choice;
            if(sc.hasNextInt()){
                choice = sc.nextInt();
                if(choice == 1) return "男";
                else if(choice == 2) return "女";
            } else {
                sc.next();
            }
            System.out.println("输入不合法！");
        }
    }

    public static double getHeight(Scanner sc){
        double height;
        while(true){
            System.out.println("请输入身高（cm）：");
            if(sc.hasNextDouble()){
                height = sc.nextDouble();
                if(height > 0) break;
            } else {
                sc.next();
            }
            System.out.println("输入不合法!");
        }
        return height;
    }

    public static double getWeight(Scanner sc){
        double weight;
        while(true){
            System.out.println("请输入体重（kg）：");
            if(sc.hasNextDouble()){
                weight = sc.nextDouble();
                if(weight > 0) break;
            } else {
                sc.next();
            }
            System.out.println("输入不合法!");
        }
        return weight;
    }

    public static double getBMI(double height,double weight){
        return weight / Math.pow((height / 100),2);
    }

    public static double getBMR(String gender,int age,double height,double weight){
        if("男".equals(gender)){
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        }
    }
}
