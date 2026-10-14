package com.ranan.extends2;

public class Test {
    public static void main(String[] args) {
        Father f = new Father();
        f.method();
        f.protectedMethod();
        f.publicMethod();
        // f.privateMethod(); 报错
    }
}
