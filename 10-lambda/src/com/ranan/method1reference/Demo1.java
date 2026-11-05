package com.ranan.method1reference;

import java.util.Arrays;

public class Demo1 {
    public static void main(String[] args) {
        // 静态方法引用 类名::静态方法
        Stu[] stu = new Stu[6];
        stu[0] = new Stu("LiHua", 18, 175.5, '男');
        stu[1] = new Stu("XiaoMing", 20, 168.0, '男');
        stu[2] = new Stu("AnRan", 19, 163.5, '女');
        stu[3] = new Stu("ZhangWei", 21, 180.0, '男');
        stu[4] = new Stu("LiMei", 18, 160.5, '女');
        stu[5] = new Stu("WangFang", 22, 166.0, '女');

//        Arrays.sort(stu, (o1, o2) -> Integer.compare(o1.getAge(), o2.getAge()));
        Arrays.sort(stu,Stu::compareByAge); // 静态方法引用
//        public static int compareByAge(Stu s1,Stu s2) {
//            return Integer.compare(s1.getAge(),s2.getAge());
//        }
        // 实例方法引用同理，只不过要创建一个对象先

        for(int i = 0;i < stu.length;++i){
            System.out.println(stu[i]);
        }
    }
}
//