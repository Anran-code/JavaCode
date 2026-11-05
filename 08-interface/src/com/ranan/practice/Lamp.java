package com.ranan.practice;

public class Lamp extends Device{
    public Lamp() {
    }

    public Lamp(String name) {
        super(name);
    }

    public Lamp(String name, Status status) {
        super(name, status);
    }
}
