package com.ranan.abstactdemo;

public class Test {
    public static void main(String[] args) {
        // A a = new A(); 报错，只能被继承
        B b = new B();
        b.show();
    }
}
