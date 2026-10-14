package com.ranan.polomorphsm2;

public class Tortoise extends Animal {
    public String name = "Tortoise";

    @Override
    public void run(){
        System.out.println("Tortoise run low!");
    }
}
