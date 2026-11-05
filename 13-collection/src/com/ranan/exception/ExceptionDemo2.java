package com.ranan.exception;

public class ExceptionDemo2 {
    public static void main(String[] args) {
        System.out.println("程序开始执行...");
        try {
            System.out.println(div(10,0));
        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println("底层方法执行失败");
        }
        System.out.println("程序结束执行...");
    }

    // 求两个数的除的结果，并返回这个结果
    public static int div(int a,int b){
        if(b == 0){
            throw new ArithmeticException("除数不能为零");
        }
        int result = a / b;
        return result;
    }
}
