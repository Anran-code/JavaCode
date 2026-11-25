package com.ranan.demo2reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo2 {
    @Test
    public void getClassInfo() {
        // 获取类的信息
        // 1.反射第一步：或者Class对象，代表拿到类
        Class c1 = Student.class;
        System.out.println(c1.getName()); // com.ranan.demo2reflect.Student
        System.out.println(c1.getSimpleName()); // Student
    }

    @Test
    public void getConstructorInfo() throws Exception{
        Class c1 = Dog.class;
        // 获取构造器对象
        Constructor[] cons = c1.getDeclaredConstructors();
        for(Constructor con : cons) {
            System.out.println(con.getName() + "(" +
                    con.getParameterCount() + ")");
        }
        // 获取单个构造器
        Constructor con = c1.getDeclaredConstructor(); // 无参
        System.out.println(con.getName() + "(" +
                con.getParameterCount() + ")");
        // 匹配参数类型
        Constructor con2 = c1.getDeclaredConstructor(String.class,int.class);
        System.out.println(con2.getName() + "(" +
                con2.getParameterCount() + ")");

        // 获取构造器对象的作用：创建对象
        con.setAccessible(true); // 绕过私有权限直接访问
        Dog dog1 = (Dog) con.newInstance();
        System.out.println(dog1);

        Dog dog2 = (Dog) con2.newInstance("123",12);
        System.out.println(dog2);
    }

    @Test
    public void getFieldInfo() throws Exception {
        Class c1 = Dog.class;
        // 获取成员变量对象
        Field[] fields = c1.getDeclaredFields();
        for(Field field : fields) {
            System.out.println(field.getName() + "(" + field.getType().getName() + ")");
        }
        // 获取单个成员变量
        Field field = c1.getDeclaredField("hobby");
        System.out.println(field.getName() + "(" + field.getType().getName() + ")");

        // 获取成员变量的目的是取值和赋值
        Dog d = new Dog("22",2);
        field.setAccessible(true);
        field.set(d,"play"); // d.setHobby("play")
        System.out.println(d);

        String hobby = (String) field.get(d);
        System.out.println(hobby);
    }

    @Test
    public void getMethodInfo() throws Exception {
        Class c1 = Dog.class;
        // 获取类方法
        Method[] methods = c1.getMethods();
        for(Method method : methods) {
            System.out.println(method.getName() + "(" + method.getParameterCount() + ")");
        }
        // 获取单个类方法
        Method method = c1.getDeclaredMethod("eat");
        System.out.println(method.getName() + "(" + method.getParameterCount() + ")");

        Method method1 = c1.getDeclaredMethod("eat",String.class);
        System.out.println(method1.getName() + "(" + method1.getParameterCount() + ")");

        // 获取类方法的目的同样也是为了调用
        Dog dog = new Dog("22",2);
        method.setAccessible(true);
        String answer = (String) method.invoke(dog);
        System.out.println(answer);

        System.out.println((String) method1.invoke(dog,"bone"));
    }
}
