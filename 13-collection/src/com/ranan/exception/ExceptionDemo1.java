package com.ranan.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionDemo1 {
    public static void main(String[] args){
        show();
        try {
            show2();
        } catch (ParseException e){
            e.printStackTrace(); // 打印异常信息
        }
    }

    // 定义一个方法认识运行时异常
    public static void show(){
        // 1.ArrayIndexOutOfBoundsException 数组越界异常
//        int[] arr = {1,2,3};
//        System.out.println(arr[3]);

        // 2.ArithmeticException 代数操作异常
//        System.out.println(10/0);

        // 3.NullPointerException 空指针异常
//        String str = null;
//        System.out.println(str.length());
    }

    // 定义一个方法认识编译时异常，并抛出
    public static void show2() throws ParseException {
        String str = "2026-10-28 12:00:00";
        // 把字符串时间解析成日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // ParseException
        Date date = sdf.parse(str);
        System.out.println(date);
    }
}
