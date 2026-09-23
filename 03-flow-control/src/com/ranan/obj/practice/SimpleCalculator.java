package com.ranan.obj.practice;
import java.util.Scanner;

// 设计一个可以执行基本数学运算（加减乘除）的计算机程序
// 用户输入两个正数字，一个运算符（+，-，*，/）
// 根据所选运算符执行运算，显示运算结果
public class SimpleCalculator {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("=========简易计算机=========");
        System.out.println("答案为：" + getAnswerOfExpression(sc));
        System.out.println("==========================");
    }

    public static double calculate(double num1,double num2,char op){
        double answer = 0;
        switch (op){
            case '+':
                answer = num1 + num2;
                break;
            case '-':
                answer = num1 - num2;
                break;
            case '*':
                answer = num1 * num2;
                break;
            case '/':
                if(num2 == 0){
                    System.out.println("除数不能为0！");
                    System.exit(1);
                }
                else answer = num1 / num2;
                break;
            default:
                System.out.println("输入有误！");
                System.exit(1);
        }
        return answer;
    }

    public static double getAnswerOfExpression(Scanner sc){
        System.out.printf("请输入表达式：");
        double num1,num2;
        char operate;

        String s = sc.nextLine();
        int index = 0;
        for(int i = 0;i < s.length();++i){
            if(s.charAt(i) == '+' || s.charAt(i) == '-'||
                    s.charAt(i) == '*' || s.charAt(i) == '/'){
                index = i;
                break;
            }
        }
        num1 = Double.parseDouble(s.substring(0,index));
        operate = s.charAt(index);
        num2 = Double.parseDouble(s.substring(index + 1));

        return calculate(num1,num2,operate);
    }

}
