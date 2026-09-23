package com.ranan.obj.scanner;
import java.util.Scanner;

public class MyScanner {
    public static void main(String[] args){
        printUserInfo();
    }

    public static void printUserInfo(){
        // 创建对象
        Scanner sc = new Scanner(System.in);

        // 获取用户输入
        System.out.println("请输入用户名");
        String username = sc.next();

        while(true){
            System.out.println("请输入年龄");
            if(sc.hasNextInt()){
                int age = sc.nextInt();
                // 打印
                System.out.println(username + ' ' + age);
                break;
            } else {
                System.out.println("输入不合法!");
                sc.next();
                continue;
            }
        }

        // 关闭标准输入
        sc.close();
    }
}
