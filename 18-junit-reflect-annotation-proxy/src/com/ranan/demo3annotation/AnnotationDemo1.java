package com.ranan.demo3annotation;

@MyBook(name = "Anran",age = 18,address = {"Beijing","Shanghai"})
public class AnnotationDemo1 {
    @A("ANRAN")
    public static void main(String[] args) {
        // 自定义注解
        // 注解本质是一个接口，Java中所有注解都是继承了Annotation接口的
        // @注解（。。。）：其实就是一个实现类对象，实现了了该注解异界以及Annotation
    }
}
