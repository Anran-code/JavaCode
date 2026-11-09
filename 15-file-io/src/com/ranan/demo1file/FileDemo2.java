package com.ranan.demo1file;

import java.io.File;
import java.util.Arrays;

public class FileDemo2 {
    public static void main(String[] args) {
        // 掌握File遍历以及文件的操作

        // 1.不存在路径
        File dir = new File("D:\\JavaCode\\15-file-io\\src\\anran1.txt"); // 不存在的路径
        File[] files1 = dir.listFiles(); // 返回null
        System.out.println(files1);

        // 2.空文件夹
        File dir2 = new File("D:\\JavaCode\\15-file-io\\src\\anran\\anran1");
        File[] files2 = dir2.listFiles();
        System.out.println(Arrays.toString(files2)); // 返回空数组

        // 3.有隐藏的一级文件，也会显示
        // 4.权限不足时，无法访问，返回null
    }
}
