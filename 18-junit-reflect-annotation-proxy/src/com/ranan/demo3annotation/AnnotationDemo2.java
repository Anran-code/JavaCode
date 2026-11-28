package com.ranan.demo3annotation;

public class AnnotationDemo2 {
    // 学习元注解：注解注解的注解
    // @Target(Element.xxx) 表示注解对象是哪些类型
    // TYPE：类/接口
    // FIELD：成员变量
    // METHOD：成员方法
    // PARAMETER：方法参数
    // CONSTRUCTOR：构造器
    // LOCAL_VARIABLE：局部变量

    // @Rentention(RententionPolicy.xxx) 声明注解的保留周期
    // SOURCE：只作用在源码阶段，字节码文件中不存在
    // CLASS（默认值）：保留到字节码文件阶段，运行阶段不存在
    // RUNTIME（开发常用）：一直保留到运行阶段


    @MyTest1
    public void func(){

    }

    @MyTest1
    private String name;
}
