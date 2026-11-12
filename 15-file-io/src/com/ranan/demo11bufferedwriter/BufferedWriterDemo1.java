package com.ranan.demo11bufferedwriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class BufferedWriterDemo1 {
    public static void main(String[] args) {
        // 目标：学习缓冲字符输出流
        try(
                Writer fw = new FileWriter("15-file-io/src/test3.txt");
                BufferedWriter bw = new BufferedWriter(fw)
        ) {
            bw.write('s');
            bw.write(98);
            bw.write("吗");
            bw.write("\r\n");

            // 写字符串
            bw.write("abc");

            char[] chars = "java".toCharArray();
            bw.write(chars);

            bw.flush(); // 刷新缓存区，关闭自带刷新
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
