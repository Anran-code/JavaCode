package com.ranan.constructor;

// 父类
public class People {
    private String name;
    private char sex;

    public People(String name,char sex){
        this.name = name;
        this.sex = sex;
    }

    public String getName(){
        return name;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public char getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }
}
