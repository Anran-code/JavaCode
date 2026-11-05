package com.ranan.practice;

public class Cat extends Pet{
    public Cat() {
    }

    public Cat(String name, int age, double score) {
        super(name, age, score);
    }

    @Override
    public void makeSound() {
        System.out.println(this.getName() + "：喵喵");
    }

    @Override
    public String toString() {
        return "Cat(" +
                this.getName() + ',' +
                this.getAge() + ',' +
                this.getScore() +
                ")";
    }

    @Override
    public void perform() {
        System.out.println(this.getName() + "正在钻圆环");
    }
}
