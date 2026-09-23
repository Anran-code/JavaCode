package com.ranan.encapsulation;

public class Student {
    public String name;
    private int age;
    private double grade;

    public Student(String name){
        this.name = name;
    }

    public void setGrade(double grade){
        if(grade >= 0 && grade <= 100) this.grade = grade;
        else System.out.println("输入有误");
    }

    public double getGrade(){
        return grade;
    }

    public void setAge(int age){
        if(age >= 0) this.age = age;
        else System.out.println("输入有误");
    }

    public int getAge(){
        return age;
    }
}
