package com.ranan.interface4;

public class Test {
    public static void main(String[] args) {
        // 接口新增的三种方式，并理解其好处
        B b = new B();
        b.go();
        A.show();
        //b.show()报错
    }
}

class B implements A {

}