package com.ranan.demo2reflect;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class SaveObjectFrame {
    // 保存任意对象的静态方法
    public static void saveObject(Object obj) throws Exception{
        PrintStream ps = new PrintStream(new FileOutputStream("18-junit-reflect-annotation-proxy/src/obj.txt",true));
        // obj看可以是任何对象
        // 只有反射可以知道对象有多少个字段
        // 1.获取class
        Class c = obj.getClass();

        String className = c.getSimpleName();
        ps.println(className);

        // 2.获取字段
        Field[] fields = c.getDeclaredFields();
        // 3.遍历字段
        for(Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = field.get(obj) + "";
            Type fieldType = field.getType();
            ps.println(fieldName + "=" + fieldValue + "(" + fieldType + ")");
        }
        ps.println("===========================================================");
    }
}
