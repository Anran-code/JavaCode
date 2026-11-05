package com.ranan.innerclass3;

public class Test2 {
    public static void main(String[] args) {
        // 匿名内部类从通常可以作为内部类的使用形式（语法），通常可以作为一个对象参数传输给方法使用
        // 1.以前的方法
//        Test2.start(new Student());
//        Test2.start(new Teacher());
        // 2.使用匿名内部类（与父类类似，这里相当于创建了实现类及其对象）
        Swim stu = new Swim(){
            @Override
            public void swimming() {
                System.out.println("学生游泳");
            }
        };
        Test2.start(stu);
    }

    // 设计一个方法，可以将接受老师、学生开始比赛
    public static void start(Swim s){
        s.swimming();
    }
}

interface Swim{
    void swimming(); // 游泳方法
}

class Student implements Swim{
    @Override
    public void swimming() {
        System.out.println("学生游泳");
    }
}

class Teacher implements Swim{
    @Override
    public void swimming() {
        System.out.println("老师游泳");
    }
}