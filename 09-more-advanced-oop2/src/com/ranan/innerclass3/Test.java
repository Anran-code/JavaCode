package com.ranan.innerclass3;

public class Test {
    public static void main(String[] args) {
//        Animal a = new Cat();
//        a.cry();
        // 匿名内部类，直接得到一个子类 + 子类对象，不需要创建对象了
        Animal a = new Animal(){
            @Override
            public void cry() {
                System.out.println("喵喵");
            }
        };
        a.cry();
    }
}

//class Cat extends Animal{
//    @Override
//    public void cry() {
//        System.out.println("喵喵");
//    }
//}