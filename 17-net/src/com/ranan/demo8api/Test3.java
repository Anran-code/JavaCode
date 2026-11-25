package com.ranan.demo8api;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test3 {
    public static void main(String[] args) {
        // 目标：BigDecimal解决小数失真问题
        double a = 0.1;
        double b = 0.2;
        System.out.println(a + b);

        // BigDecimal解决
//        BigDecimal a1 = new BigDecimal(Double.toString(a));
//        BigDecimal a2 = new BigDecimal(Double.toString(b));
        // 优化：
        BigDecimal a1 = BigDecimal.valueOf(a);
        BigDecimal a2 = BigDecimal.valueOf(b);

        BigDecimal a3 = a1.add(a2);
        double result = a3.doubleValue();
        System.out.println(result);

        // 除法：
        BigDecimal i = BigDecimal.valueOf(0.1);
        BigDecimal j = BigDecimal.valueOf(0.3);

        BigDecimal k = i.divide(j,2, RoundingMode.HALF_UP); // 抛异常
        // 保留两位，四舍五入取整（取整模式）
        System.out.println(k);
    }
}
