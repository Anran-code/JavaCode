package com.ranan.interface3;

public class Test {
    public static void main(String[] args) {
        // 1.定义学生类
        // 2.创建数据
        Student[] students = new Student[10];
        students[0] = new Student("a",'男',90);
        students[1] = new Student("b",'女',95);
        students[2] = new Student("c",'女',93);
        students[3] = new Student("d",'男',88);
        students[4] = new Student("e",'女',97);
        students[5] = new Student("f",'男',91);
        students[6] = new Student("g",'男',85);
        students[7] = new Student("h",'男',70);
        students[8] = new Student("i",'女',75);
        students[9] = new Student("j",'女',82);

        // 3.实现两套方案 --> 一个接口的两种实现
        // 定义一个接口：必须完成打印学生信息和平均分
        // 定义第一个实现类，实现接口，打印学生信息、平均分
        // 定义第二个实现类，实现接口，打印学生信息（附加男女人数），平均分（要求去掉最高最低分）
        ClassDataInter cdi1 = new ClassDataInterImplement1(students);
        cdi1.printAllStudentInfo();
        cdi1.printAverageScore();

        System.out.println("=========================================");

        ClassDataInter cdi2 = new ClassDataInterImplement2(students);
        cdi2.printAllStudentInfo();
        cdi2.printAverageScore();
    }
}
