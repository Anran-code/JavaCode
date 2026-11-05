package com.ranan.innerclass;

public class InnerClassDemo1 {
    public static void main(String[] args) {
        Outer.Inner oi = new Outer().new Inner();
        oi.show();
        oi.setName("Anran");
        // 成员内部类访问外部类成员的特点（拓展）：
        // 1.成员对象中可以直接访问外部类的静态成员，也可以直接访问外部类的实例成员
        // 2.成员内部类的实例方法中，可以直接拿到当前寄生的外部类对象，外部类名.this
        People.Heart ph = new People().new Heart();
        ph.show();
    }
}
