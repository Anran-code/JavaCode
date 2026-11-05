package com.ranan.practice;

public class Dog extends Pet{
    public Dog() {
    }

    public Dog(String name, int age, double score) {
        super(name, age, score);
    }

    @Override
    public void makeSound() {
        System.out.println(this.getName() + "：汪汪");
    }

    @Override
    public String toString() {
        return "Dog(" +
                this.getName() + ',' +
                this.getAge() + ',' +
                this.getScore() +
                ")";
    }

    @Override
    public void perform() {
        System.out.println(this.getName() + "正在接飞盘");
    }
}
