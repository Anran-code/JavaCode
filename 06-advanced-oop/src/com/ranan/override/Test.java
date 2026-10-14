package com.ranan.override;

public class Test {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.cry();
    }
}

class Animal{
    public void cry(){
        System.out.println("动物叫");
    }
}

class Dog extends Animal{
    @Override
    public void cry() {
        System.out.println("大狗叫");
    }
}
