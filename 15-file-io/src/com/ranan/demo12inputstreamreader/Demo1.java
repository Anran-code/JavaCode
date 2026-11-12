package com.ranan.demo12inputstreamreader;

import java.io.*;

public class Demo1 {
    public static void main(String[] args) {
        // 演示一个问题：不同编码转换的乱码问题
        // 代码：UTF-8 文件：UTF-8 读取不乱码
        // 代码：UTF-8 文件：GBK 读取乱码
        try(
                // 先提取文件的原始字节流
                InputStream is = new FileInputStream("15-file-io/src/test1_GBK.txt");
                // 指定字符集把字节输入流转换为字符输入流
                Reader isr = new InputStreamReader(is,"GBK");
                // 创建缓冲字符输入流
                BufferedReader br = new BufferedReader(isr);
        ) {
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
