package com.ranan.demo8filewriter;

import java.io.FileWriter;
import java.io.Writer;

public class FileWriterDemo1 {
    public static void main(String[] args) {
        // 目标：学习字符输出流
        try(
                Writer fw = new FileWriter("15-file-io/src/test3.txt");
                ) {
            fw.write('s');
            fw.write(98);
            fw.write("吗");
            fw.write("\r\n");

            // 写字符串
            fw.write("abc");

            char[] chars = "java".toCharArray();
            fw.write(chars);

            fw.flush(); // 刷新缓存区，关闭自带刷新
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
