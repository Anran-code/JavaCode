package com.ranan.practice;

public class WashMachine extends Device{
    public WashMachine() {
    }

    public WashMachine(String name) {
        super(name);
    }

    public WashMachine(String name, Status status) {
        super(name, status);
    }
}
