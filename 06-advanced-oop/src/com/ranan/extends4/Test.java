package com.ranan.extends4;

public class Test {
    public static void main(String[] args) {
        Son son = new Son();
        son.show();
    }
}

class Father{
    String name = "Father";

    public void fun(){
        System.out.println("Father.fun()");
    }
}

class Son extends Father{
    String name = "Son";

    public void show(){
        System.out.println(this.name);
        System.out.println(super.name);

        fun();
        super.fun();
    }

    public void fun(){
        System.out.println("Son.fun()");
    }
}

