package com.ranan.practice;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Pet[] pets = new Pet[6];

        pets[0] = new Cat("哈基米",5,85.5);
        pets[1] = new Cat("咪咪",1,92);
        pets[2] = new Cat("牢大",11,91.5);
        pets[3] = new Dog("旺财",4,99);
        pets[4] = new Dog("大狗",7,95);
        pets[5] = new Dog("黑哥们",3,87.5);

        Arrays.sort(pets,new PetScoreComparator());

        for(int i = 0;i < pets.length;++i){
            System.out.println(pets[i]);
            pets[i].makeSound();
            Test.startTraining(pets[i]);
            System.out.println("===================================");
        }

        startTraining(new Trainable() {
            @Override
            public void perform() {
                System.out.println("神秘嘉宾的表演");
            }
        });
    }

    public static void startTraining(Trainable t){
        t.perform();
    }
}
