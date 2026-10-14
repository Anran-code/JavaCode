package com.ranan.polomorphsm2;

public class Wolf extends Animal {
    public String name = "Wolf";

    @Override
    public void run() {
        System.out.println("Wolf run fast!");
    }

    public void bite(){
        System.out.println("Wolf bite!");
    }
}
