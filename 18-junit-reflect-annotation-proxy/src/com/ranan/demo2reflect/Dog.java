package com.ranan.demo2reflect;

import lombok.Data;

@Data
public class Dog {
    private String name;
    private int age;
    private String hobby;

    // con.getParameterCount() = 0
    private Dog() {

    }

    // con.getParameterCount() = 1
    private Dog(String name) {
        this.name = name;
    }

    // con.getParameterCount() = 2
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String eat() {
        System.out.println("Dog eat");
        return "thanks";
    }

    public String eat(String food) {
        System.out.println("Dog eat " + food);
        return "thanks";
    }
}
