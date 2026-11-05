package com.ranan.practice;

public class Television extends Device{
    public Television() {
    }

    public Television(String name) {
        super(name);
    }

    public Television(String name, Status status) {
        super(name, status);
    }
}
