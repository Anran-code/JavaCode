package com.ranan.polymorphsm1;

public class Tortoise extends Animal{
    public String name = "Tortoise";

    @Override
    public void run(){
        System.out.println("Tortoise run low!");
    }
}
