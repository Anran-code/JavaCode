package com.ranan.demo2reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectDemo3 {
    public static void main(String[] args) throws Exception{
        // 反射的基本作用
        // 1.类的全部成分的获取
        // 2.可以破坏封装性
        // 3.可以绕过泛型的约束
        List<String> list = new ArrayList<>();
        list.add("123");
        // list.add(1); 报错

        Class c1 = list.getClass(); // c1 == ArrayList.class
        // 获取add方法
        Method add = c1.getDeclaredMethod("add",Object.class);
        add.invoke(list,9.1); // 翻墙
        add.invoke(list,true); // 翻墙

        System.out.println(list);
    }
}
