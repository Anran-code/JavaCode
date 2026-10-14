package com.ranan.constructor;

public class Test {
    public static void main(String[] args) {
        Son son = new Son();
    }
}

class Father{
    public Father(){
        System.out.println("Father's constructor");
    }
}

class Son extends Father{
    public Son(){
        // super() 默认存在
        System.out.println("Son's constructor");
    }
}
