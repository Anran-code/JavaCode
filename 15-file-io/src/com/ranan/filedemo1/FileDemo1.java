package com.ranan.filedemo1;

import java.io.File;

public class FileDemo1 {
    public static void main(String[] args) throws Exception{
        // 目标：创建File对象创建对象代表文件（文件/目录），搞清楚操作文件的方法
        // 1.创建File对象看文件信息
        File f1 = new File("C:\\Users\\A\\Pictures\\Screenshots\\屏幕截图 2026-10-31 004936.png"); // 或者用正斜杠

        System.out.println(f1.length());  // 字节数
        System.out.println(f1.getName());
        System.out.println(f1.isFile()); // true
        System.out.println(f1.isDirectory()); // false

        System.out.println("=========================");

        // 2.使用相对路径定位文件
        File f2 = new File("15-file-io/src/anran.txt");
        System.out.println(f2.length());  // 6 字节数

        System.out.println("=========================");

        // 3.创建对象代表不存在的路径
//        File f3 = new File("15-file-io/src/anran1.txt");
//        System.out.println(f3.exists());
//        System.out.println(f3.createNewFile());

        // 4.创建对象代表不存在的文件夹路径
        File f4 = new File("15-file-io/src/anran");
        System.out.println(f4.mkdir()); // mkdir() 只能创建一级文件夹

        System.out.println("=========================");

        File f5 = new File("15-file-io/src/anran/anran1/anran2");
        System.out.println(f5.mkdirs()); // mkdirs() 创建多级文件夹

        System.out.println("=========================");

        // 5.创建File文件代表存在的文件/文件夹，然后删除
        f5.delete(); // 只能删除空文件夹，不能删除非空文件夹 f4.delete()是不可以的

        // 6.获取某个文件夹下的全部一级文件
        File f6 = new File("15-file-io/src");
        String[] names = f6.list();
        for(String name : names){
            System.out.println(name);
        }
        System.out.println("=========================");

        File[] files = f6.listFiles();
        for(File file : files){
            System.out.println(file.getAbsoluteFile()); // 绝对路径
        }
    }
}
