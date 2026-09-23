package com.ranan.staticfield;

public class Test {
    public static void main(String[] args){
        Student.name = "LiHua";

        Student s1 = new Student();
        s1.name = "LiMing";

        Student s2 = new Student();
        s2.name = "LiPing";

        System.out.println(Student.name);
        System.out.println(Student.number);
    }
}
