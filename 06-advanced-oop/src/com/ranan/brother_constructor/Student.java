package com.ranan.brother_constructor;

public class Student {
    private String name;
    private int age;
    private char sex;
    private String school_name;

    public Student(String name, int age, char sex, String school_name) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.school_name = school_name;
    }

    public Student(String name, int age, char sex) {
        this(name,age,sex,"洪江一中");
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
