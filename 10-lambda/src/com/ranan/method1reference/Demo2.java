package com.ranan.method1reference;

import java.util.Arrays;
import java.util.Comparator;

public class Demo2 {
    public static void main(String[] args) {
        // 特定类型的方法引用
        // 需求：有一个字符串数组，里面有一些人的名字都是英文，按照名字首字母升序排序
        String[] names = {"Tom","Jeery","Mike","angela","曹操","Jack","Rose","caocao","Dlei","Andy","Bobi"};

        // 把这个数组进行排序
        //Arrays.sort(names); // 默认按照首字母的编号升序排列
        // 要求：忽略大小写排序
        // Arrays.sort(names, (o1,o2) -> o1.compareToIgnoreCase(o2));  // java提供了按照首字母比较的方法，且忽略大小写);

        // 特定类型的方法引用：如果某个Lambda表达式只是里只是调用一个特定类型的实例方法，并且前面参数列表中的第一个参数作为方法的主调，后面的所有参数作为该实例方法的入参的，则可采用特定类型的方法引用
        Arrays.sort(names, String::compareToIgnoreCase);

        System.out.println(Arrays.toString(names));
    }
}
