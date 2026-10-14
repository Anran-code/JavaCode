package com.ranan.polymorphsm1;

public class Test {
    public static void main(String[] args) {
        Animal a1 = new Wolf();
        Animal a2 = new Tortoise(); // 解耦合的（高内聚，低耦合）
        a1.run(); // 方法：编译看左，运行看右
        a2.run();

        System.out.println(a1.name); // 成员变量：编译看左，运行看左（实际上是变量不存在多态复写，这要和就近原则区分）
        System.out.println(a2.name);

        go(a1);
        go(a2);

        // 若采用子类特殊方法，不能使用多态，直接子类定义
        Wolf w = new Wolf();
        w.bite();
        // a1.bite() 是错的，无法通过编译
    }

    // 多态的高拓展性
    public static void go(Animal a){
        a.run();
    }
}
