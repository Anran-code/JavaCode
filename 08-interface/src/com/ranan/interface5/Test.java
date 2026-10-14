package com.ranan.interface5;

public class Test {
    public static void main(String[] args) {
        // 接口的注意事项
        Dog dog = new Dog();
        dog.show(); // Animal show
        dog.go(); // A2 show
    }
}

// 1.接口与接口可以多继承，一个接口可以同时继承多个接口
// 类与类：单继承
// 类与接口：可以多实现
// 接口与接口：可以多继承
interface A{
    void show1();
}

interface B{
    void show2();
}

interface C extends A,B{
    void show3();
}

class D implements A,B,C{
    @Override
    public void show3() {

    }

    @Override
    public void show1() {

    }

    @Override
    public void show2() {

    }
}

// 2.一个接口继承多个接口，如果多个接口中存在方法签名冲突，则此时不支持多继承，也不支持多实现
interface A1{
    void show();
}

interface B1{
    String show();
}

//interface C1 extends A1,B1{
//
//} 报错

// 3.一个类继承了父类，同时又实现了接口，如果父类和接口中有同名的方法，实现类会优先使用父类的
interface A2{
    default void show(){
        System.out.println("A2 show");
    }
}

class Animal{
    public void show(){
        System.out.println("Animal show");
    }
}

class Dog extends Animal implements A2{
    // 中转实现
    public void go(){
        A2.super.show();
    }
}

// 4.一个类实现了多个接口，如果多个接口中存在同名的默认方法，可以不冲突，这个类重写方法即可
interface A3{
    default void show(){
        System.out.println("A3 show");
    }
}

interface B3{
    default void show(){
        System.out.println("B3 show");
    }
}

class C2 implements A3,B3{
    @Override
    public void show() {
        // 重写找调用谁的
        A3.super.show();
        B3.super.show();
    }
}
