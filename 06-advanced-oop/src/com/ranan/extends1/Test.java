package com.ranan.extends1;

public class Test {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setName("LiHua");
        teacher.setSex('m');
        teacher.setSkill("Reading");
        System.out.println(teacher.getName());
        System.out.println(teacher.getSex());
        System.out.println(teacher.getSkill());
    }
}
