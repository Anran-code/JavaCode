package com.ranan.demo8api;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test1 {
    public static void main(String[] args) {
        // 目标：掌握java提供的获取时间的方案（项目前置）
        // JDK8之前的方案：Date获取此时时间
        Date d = new Date();
        System.out.println(d);

        // 格式化：SimpleDateFormat简单日期格式化，格式化日期对象成为我们喜欢的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EEE a");
        String result = sdf.format(d);
        System.out.println(result);

        // JDK8后方案：LocalDate LocalTime LocalDateTime 获取此刻日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now.getMinute());
        System.out.println(now.getDayOfYear());

        // 格式化DateTimeFormatter
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss EEE a");
        System.out.println(dtf.format(now));
    }
}
