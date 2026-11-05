package com.ranan.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionDemo4 {
    public static void main(String[] args) {
        // 掌握异常的处理方案1：底层异常都抛出去给最外层调用者，最外层捕获异常，记录异常，响应合适信息给用户观看
        try {
            show2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show2() throws ParseException {
        String str = "2026-10-28 12:00:00";
        // 把字符串时间解析成日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // ParseException
        Date date = sdf.parse(str);
        System.out.println(date);
    }
}
