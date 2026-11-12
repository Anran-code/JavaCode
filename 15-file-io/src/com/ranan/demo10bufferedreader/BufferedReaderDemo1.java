package com.ranan.demo10bufferedreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class BufferedReaderDemo1 {
    public static void main(String[] args) {
        // 学习缓冲字符输入流
        // 1.创建文件字符输入流与源文件接通
        try(
                Reader fr = new FileReader("15-file-io/src/test1.txt");
                BufferedReader br = new BufferedReader(fr);
        ) {
//            // 2.定义一个字符数组
//            char[] buffer = new char[1024];
//            int len;
//            while((len = br.read(buffer)) != -1){
//                String str = new String(buffer,0,len);
//                System.out.println(str);
//            }

            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
