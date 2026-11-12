package com.ranan.demo7filereader;

import java.io.FileReader;
import java.io.Reader;

public class FileReaderDemo1 {
    public static void main(String[] args) {
        // 目标：学习文件字符输入流
        // 1.创建文件字符输入流与源文件接通
        try(
                Reader fr = new FileReader("15-file-io/src/test1.txt");
                ) {
            // 2.定义一个字符数组
            char[] buffer = new char[3];
            int len;
            while((len = fr.read(buffer)) != -1){
                String str = new String(buffer,0,len);
                System.out.println(str);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
