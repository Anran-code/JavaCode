package com.ranan.obj.practice;
import java.util.Scanner;

// 生成一个指定长度的验证码，由数字、大小写字母构成
// '0' ~ '9'：48 ~ 57
// 'A' ~ 'Z'：65 ~ 90
// 'a' ~ 'z'：97 ~ 122
public class VerificationCode {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========验证码生成器=========");
        System.out.println("验证码：" + getVerificationCode(getLength(sc)));
        System.out.println("============================");
    }

    public static int getLength(Scanner sc){
        int len;
        while(true){
            System.out.printf("请输入验证码长度：");
            if(sc.hasNextInt()){
                len = sc.nextInt();
                if(len > 0) break;
            } else {
                sc.next();
            }
            System.out.println("输入不合法!");
        }
        return len;
    }

    public static char indexToChar(int i){
        // '0' ~ '9'：48 ~ 57
        if(i >= 0 && i <= 9){
            return (char)(48 + i);
        } else if(i >= 10 && i <= 35){ // 'A' ~ 'Z'：65 ~ 90
            return (char)(55 + i);
        } else if(i >= 36 && i <= 61){ // 'a' ~ 'z'：97 ~ 122
            return (char)(61 + i);
        } else {
            return ' ';
        }
    }

    public static String getVerificationCode(int len){
        StringBuilder code = new StringBuilder();
        for(int i = 0;i < len;i++){
            int index = (int)(Math.random() * 62);
            code.append(indexToChar(index));
        }
        return code.toString();
    }
}
