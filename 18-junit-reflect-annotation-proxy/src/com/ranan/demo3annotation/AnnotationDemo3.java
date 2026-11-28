package com.ranan.demo3annotation;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationDemo3 {
    // 学习解析注释
    @Test
    public void parseClass() throws Exception {
        // 1.获取类对象
        Class c1 = Demo.class;
        // 2.判断类上是否有MyTest2注释
        if(c1.isAnnotationPresent(MyTest2.class)) {
            // 3.获取注释对象
            MyTest2 myTest2 = (MyTest2) c1.getAnnotation(MyTest2.class);
            // 4.获取注释属性值
            String value = myTest2.value();
            double price = myTest2.price();
            String[] address = myTest2.adddress();

            System.out.println(value);
            System.out.println(price);
            System.out.println(Arrays.toString(address));
        }
    }

    @Test
    public void parseMethod() throws Exception {
        // 1.获取类对象
        Class c1 = Demo.class;
        // 2.获取方法对象
        Method method = c1.getDeclaredMethod("go");
        if(method.isAnnotationPresent(MyTest2.class)) {
            // 3.获取注释对象
            MyTest2 myTest2 = (MyTest2) method.getAnnotation(MyTest2.class);
            // 4.获取注释属性值
            String value = myTest2.value();
            double price = myTest2.price();
            String[] address = myTest2.adddress();

            System.out.println(value);
            System.out.println(price);
            System.out.println(Arrays.toString(address));
        }
    }
}
