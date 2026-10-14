package com.ranan.extends2;

public class Father {
    private void privateMethod(){
        System.out.println("privateMethod");
    }

    void method(){
        System.out.println("privateMethod");
    }

    protected void protectedMethod(){
        System.out.println("protectedMethod");
    }

    public void publicMethod(){
        System.out.println("publicMethod");
    }

    public static void main(String[] args) {
        Father f = new Father();
        f.privateMethod();
        f.method();
        f.protectedMethod();
        f.publicMethod();
    }
}
