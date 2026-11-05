package com.ranan.code;

import java.util.Arrays;

public class CodeDemo1 {
    public static String NAME;
    public static String[] array = new String[5];
    // 静态代码块，与类一起加载，自动执行一次
    static {
        System.out.println("静态代码块执行");
        // 完成对静态资源的初始化
        NAME = "Anran";
        array[0] = "A";
        array[1] = "B";
    }

    public static void main(String[] args) {
        System.out.println("main方法执行");
        System.out.println(Arrays.toString(array));
    }
}
