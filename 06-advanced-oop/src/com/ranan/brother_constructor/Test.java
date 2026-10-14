package com.ranan.brother_constructor;

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student("LiHua",16,'男',"怀化三中");
        Student s2 = new Student("LiQiang",17,'女');

        System.out.println(s2.getSchool_name());
    }
}
