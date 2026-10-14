package com.ranan.interface2;

public class Test {
    public static void main(String[] args) {
        // 1.接口的好处：多继承+多态
        Driver d = new Student();
        People p = new Student();
        BoyFriend b = new Student();

        // 2.解耦合（灵活替换）
        Driver d2 = new Student();
        Driver d3 = new Teacher();
    }
}

interface Driver{}
interface BoyFriend{}

class People{}
class Student extends People implements Driver,BoyFriend{}
class Teacher extends People implements Driver{}
