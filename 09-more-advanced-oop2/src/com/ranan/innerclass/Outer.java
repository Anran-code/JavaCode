package com.ranan.innerclass;

public class Outer {
    public static String school_name = "Anran";

    public void test(){
    }

    // 成员内部类：无static修饰，属于外部类的对象持有（ph）
    public class Inner {
        private String name;

        // 构造器
        public Inner(){
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void show(){
            System.out.println("show");
            // 1.成员对象中可以直接访问外部类的静态成员，也可以直接访问外部类的实例成员
            school_name = "123";
            test();
            // 2.成员内部类的实例方法中，可以直接拿到当前寄生的外部类对象，外部类名.this
            System.out.println(Outer.this);
        }
    }
}
