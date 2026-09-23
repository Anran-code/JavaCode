package com.ranan.obj.basic;

public class Basic {
    public static void main(String[] args){
        Basic Add = new Basic();
        System.out.println(Add.add(1,2));
        System.out.println(muti(1.2,2.4));
        System.out.println(getAsciiOfLetter('A'));
        System.out.println(randomKeyNumber(6));
    }

    public int add(int a, int b){
        return a + b;
    }

    public static double muti(double a,double b){
        return a * b;
    } 

    // 强制类型转换
    public static int getAsciiOfLetter(char c){
        return (int)c;
    }

    // 生成验证码
    public static String randomKeyNumber(int len){
        String key = "";
        for(int i = 0;i < len;i++){
            int num = (int)(Math.random() * 10);
            key += num;
        }
        return key;
    }
}
