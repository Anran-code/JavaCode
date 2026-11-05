package com.ranan.genericity2;

public class GenericDemo3 {
    public static void main(String[] args) {
        // 目标：学习泛型接口
        // 需求：对学生数据/老师数据都要增删改查
        StudentData sd = new StudentData();
        sd.add(new Student());
        sd.delete(new Student());
        sd.query(1);
        sd.update(new Student());
    }
}
