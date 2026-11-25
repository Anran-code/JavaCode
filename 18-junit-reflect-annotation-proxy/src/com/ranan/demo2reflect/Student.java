package com.ranan.demo2reflect;

import lombok.Data;

@Data
public class Student {
    private String name;
    private int age;

    public Student() {

    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
