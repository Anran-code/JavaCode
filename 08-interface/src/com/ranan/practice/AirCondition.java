package com.ranan.practice;

public class AirCondition extends Device{
    public AirCondition() {
    }

    public AirCondition(String name) {
        super(name);
    }

    public AirCondition(String name, Status status) {
        super(name, status);
    }
}
