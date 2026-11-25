package com.ranan.demo2reflect;

public class ReflectDemo4 {
    public static void main(String[] args) throws Exception{
        // 搞清楚反射的应用，做框架的技术
        Dog dog = new Dog("dog",2);
        SaveObjectFrame.saveObject(dog);
        System.out.println("======================================");
        Student student = new Student("LiHua",23);
        SaveObjectFrame.saveObject(student);
        System.out.println("======================================");
        Teacher teacher = new Teacher("ZhangHua",18,"java",5000,"703",'m',"1234556777");
        SaveObjectFrame.saveObject(teacher);
        System.out.println("======================================");
    }
}
