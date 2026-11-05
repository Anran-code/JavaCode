package com.ranan.lambda;

import com.ranan.innerclass3.Stu;
import java.util.Arrays;

public class LambdaDemo2 {
    public static void main(String[] args) {
        // 完成给数组排序，理解其中匿名内部类的用法
        Stu[] stu = new Stu[6];
        stu[0] = new Stu("LiHua", 18, 175.5, '男');
        stu[1] = new Stu("XiaoMing", 20, 168.0, '男');
        stu[2] = new Stu("AnRan", 19, 163.5, '女');
        stu[3] = new Stu("ZhangWei", 21, 180.0, '男');
        stu[4] = new Stu("LiMei", 18, 160.5, '女');
        stu[5] = new Stu("WangFang", 22, 166.0, '女');

        // public static void sort(T[] a,Comparator<T> c) 排序，函数式接口Lambda表达式简化
//        Arrays.sort(stu, new Comparator<Stu>() {
//            @Override
//            public int compare(Stu o1, Stu o2) {
//                return Integer.compare(o1.getAge(), o2.getAge());
//            }
//        });

        Arrays.sort(stu, (Stu o1,Stu o2) -> {
            return Integer.compare(o1.getAge(), o2.getAge());
        });

        // Lambda表达式进一步简化：
        // 1.参数类型全部可以省略不写
        // 2.如果只有一个参数，参数类型省略的同时"()"也可以省略，但多个参数不行
        // 3.Lambda表达式只有一行代码，大括号可以不写，省略分号，去掉return

        // 1.参数类型全部可以省略不写
        Arrays.sort(stu, (o1,o2) -> {
            return Integer.compare(o1.getAge(), o2.getAge());
        });

        // 2.如果只有一个参数，参数类型省略的同时"()"也可以省略，但多个参数不行
//        btn.addActionListener( e -> {
//            System.out.println("按钮按下");
//        });

        // 3.Lambda表达式只有一行代码，大括号可以不写，省略分号，去掉return
        Arrays.sort(stu, (o1,o2) -> Integer.compare(o1.getAge(), o2.getAge()));

        for(int i = 0;i < stu.length;++i){
            System.out.println(stu[i]);
        }
    }
}
