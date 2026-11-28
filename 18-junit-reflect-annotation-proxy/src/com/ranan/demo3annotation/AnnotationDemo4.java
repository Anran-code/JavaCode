package com.ranan.demo3annotation;

import java.lang.reflect.Method;

public class AnnotationDemo4 {
    public static void main(String[] args) throws Exception{
        // 搞清楚注解的应用场景：模拟junit框架。
        // 获取类对象，获取所有方法，有注解的就执行
        Class c = AnnotationDemo4.class;
        Method[] methods = c.getDeclaredMethods();

        AnnotationDemo4 annotationDemo4 = new AnnotationDemo4();

        for(Method method : methods) {
            if(method.isAnnotationPresent(MyTest.class)) {
                MyTest myTest = method.getDeclaredAnnotation(MyTest.class);
                int count = myTest.count(); // 解析注解属性
                for(int i = 0;i < count;++i) {
                    method.invoke(annotationDemo4);
                }
            }
        }
    }

    @MyTest
    public void Test1() {
        System.out.println("Test1方法执行了");
    }

    public void Test2() {
        System.out.println("Test2方法执行了");
    }

    @MyTest(count = 3)
    public void Test3() {
        System.out.println("Test3方法执行了");
    }

    public void Test4() {
        System.out.println("Test4方法执行了");
    }
}
