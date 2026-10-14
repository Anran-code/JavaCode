package com.ranan.constructor;

public class Test2 {
    public static void main(String[] args) {
        Teacher t = new Teacher("Python","LiHua",'男');
        System.out.println(t.getName());
        System.out.println(t.getSex());
        System.out.println(t.getSkill());
    }
}
