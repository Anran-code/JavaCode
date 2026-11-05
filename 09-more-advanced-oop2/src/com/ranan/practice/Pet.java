package com.ranan.practice;

public abstract class Pet implements Trainable{
    private String name;
    private int age;
    private double score;

    public Pet(){}

    public Pet(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public abstract void makeSound();

    @Override
    public abstract String toString();
}
