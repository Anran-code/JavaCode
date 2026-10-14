package com.ranan.constructor;

// 子类
public class Teacher extends People {
    private String skill;

    public Teacher(String skill,String name,char sex){
        super(name,sex);
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

}
