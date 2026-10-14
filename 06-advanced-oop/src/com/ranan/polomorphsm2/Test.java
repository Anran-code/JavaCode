package com.ranan.polomorphsm2;

public class Test {
    public static void main(String[] args) {
        Animal a1 = new Wolf();
        Animal a2 = new Tortoise(); // 自动类型转换
        a1.run(); // 方法：编译看左，运行看右
        a2.run();

        System.out.println(a1.name); // 成员变量：编译看左，运行看左（实际上是变量不存在多态复写，这要和就近原则区分）
        System.out.println(a2.name);

        // 强制类型转换 Animal -> Wolf，确认编译不出错
        // Java建议强转前，先判断类型
        if(a1 instanceof Wolf){
           Wolf t1 = (Wolf)a1;
           t1.bite();
        }

        // Wolf t1 = (Wolf)a2; 是不行的，会报类型转换异常(ClassCastException)
    }
}
