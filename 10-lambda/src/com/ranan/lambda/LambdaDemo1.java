package com.ranan.lambda;

public class LambdaDemo1 {
    public static void main(String[] args) {
        // 认识lambda表达式，搞清楚基本作用
        Animal a = new Animal() {
            @Override
            public void cry() {
                System.out.println("meow meow");
            }
        };
        a.cry();

        // Lambda表达式只能替代函数式接口的匿名内部类
        // 函数式接口：有且只有一个抽象方法的接口
        Swim s = () -> {
            System.out.println("swim");
        };
        s.swimming();
        // 等价于：
//        Swim s = new Swim() {
//            @Override
//            public void swiming() {
//                System.out.println("swim");
//            }
//        }
    }
}

abstract class Animal{
    public abstract void cry();
}

// 函数式接口：
@FunctionalInterface // 声明函数式接口的注解
interface Swim{
    void swimming();
}