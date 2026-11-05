package com.ranan.innerclass2;
// 外部类
public class Outer {
    public static String name = "Anran";
    private int age = 10;
    // 静态内部类
    public static class Inner{
        public void show(){
            // 1.内部类可以访问外部类的静态成员变量，因为是随着类一起初始化的
            System.out.println(name);
            // 2.内部类不可以访问外部类的实例子成员变量！！！有原因就在于需要先创建外部类实例，而静态内部类属于外部类，拿不到外部类对象
            //System.out.println(age); 报错
        }
    }
}
