package com.ranan.encapsulation;

public class Test {
    public static void main(String[] args){
        Student s1 = new Student("小明");
        s1.setAge(20);
        s1.setGrade(50);

        System.out.println(s1.getAge());
        System.out.println(s1.getGrade());
    }
}
