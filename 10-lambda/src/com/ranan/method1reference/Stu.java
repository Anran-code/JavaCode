package com.ranan.method1reference;

public class Stu {
    private String name;
    private int age;
    private double height;
    private char sex;

    public Stu(){}

    public Stu(String name, int age, double height, char sex) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", sex=" + sex +
                '}';
    }

    public static int compareByAge(Stu s1,Stu s2) {
        return Integer.compare(s1.getAge(),s2.getAge());
    }
}
