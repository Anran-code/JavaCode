package com.ranan.abstractdemo2;

public class Test {
    public static void main(String[] args) {
        // 更好的多态
        Animal a = new Dog();
        a.cry();
    }
}
