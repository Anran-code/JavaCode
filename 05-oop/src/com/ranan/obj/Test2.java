package com.ranan.obj;

public class Test2 {
    public static void main(String[] args){
        Student s1 = new Student("小明");
        s1.chinese = 90.0;
        s1.math = 80.0;

        s1.getAllScore();
        s1.love("丽华");
    }
}
