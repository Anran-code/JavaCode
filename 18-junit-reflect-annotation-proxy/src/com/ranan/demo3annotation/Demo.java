package com.ranan.demo3annotation;

@MyTest2(value = "123",price = 120,adddress = {"123"})
public class Demo {
    @MyTest2(value = "456",price = 100,adddress = {"456"})
    public void go(){
    }
}
