package com.ranan.practice;

public class Device implements Switch{
    private String name;
    private Status status = Status.CLOSE;

    @Override
    public void press() {
        if(status == Status.CLOSE) status = Status.OPEN;
        else status = Status.CLOSE;
    } // 父类实现了，子类不需要再重写

    public Device() {
    }

    public Device(String name){
        this.name = name;
    }

    public Device(String name,Status status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


