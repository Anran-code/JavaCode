package com.ranan.code;

public class CodeDemo2 {
    private String name;
    private String[] direction = new String[4];
    // 实例代码块，属于对象，每次创建对象优先执行一次
    {
        System.out.println("实例代码块执行");
        name = "Anran";
        direction[0] = "East";
        direction[1] = "South";
        direction[2] = "West";
        direction[3] = "North";
    }

    public static void main(String[] args) {
        System.out.println("main开始执行");
        new CodeDemo2();
        System.out.println("main结束执行");
    }
}
