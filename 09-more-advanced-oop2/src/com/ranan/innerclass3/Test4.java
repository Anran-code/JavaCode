package com.ranan.innerclass3;

import java.util.Arrays;
import java.util.Comparator;

public class Test4 {
    public static void main(String[] args) {
        // 完成给数组排序，理解其中匿名内部类的用法
        Stu[] stu = new Stu[6];
        stu[0] = new Stu("LiHua", 18, 175.5, '男');
        stu[1] = new Stu("XiaoMing", 20, 168.0, '男');
        stu[2] = new Stu("AnRan", 19, 163.5, '女');
        stu[3] = new Stu("ZhangWei", 21, 180.0, '男');
        stu[4] = new Stu("LiMei", 18, 160.5, '女');
        stu[5] = new Stu("WangFang", 22, 166.0, '女');

        // public static void sort(T[] a,Comparator<T> c) 排序
        Arrays.sort(stu, new Comparator<Stu>() {
            @Override
            public int compare(Stu o1, Stu o2) {
                // 指定比较规则（即重写compare方法）
                // 如果你认为左边对象大于右边对象，返回正整数，反之负整数
                // 相等返回 0
//                if(o1.getHeight() > o2.getHeight()) return 1;
//                else if(o1.getHeight() < o2.getHeight()) return -1;
//                else return 0;

                // 匿名类等价于：
//                import java.util.Comparator;
//
//                public class StuAgeComparator implements Comparator<Stu> {
//                    @Override
//                    public int compare(Stu o1, Stu o2) {
//                        return Integer.compare(o1.getAge(), o2.getAge());
//                    }
//                }
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });

        for(int i = 0;i < stu.length;++i){
            System.out.println(stu[i]);
        }
    }
}
