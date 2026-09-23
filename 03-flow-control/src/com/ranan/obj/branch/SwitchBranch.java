package com.ranan.obj.branch;

import java.util.Scanner;
import java.util.SplittableRandom;

public class SwitchBranch {
    public static void main(String[] args) {
        getGender();
    }

    public static void getGender(){
        System.out.println("请输入性别：（男/女）");
        Scanner sc = new Scanner(System.in);
        String gender = sc.next();

        switch (gender){
            case "男":
                System.out.println("你是男性");
                break;
            case "女":
                System.out.println("你是女性");
                break;
            default:
                System.out.println("输入有误");
                break;
        }
    }
}
