package com.ranan.demo4fileinputstream;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileInputStreamDemo1 {
    public static void main(String[] args) throws Exception {
        // 掌握文件字节输入流流读取文件中的字节数组到内存中来
        // 1.创建文件字节输入流管道于源文件接通
        InputStream is = new FileInputStream("15-file-io/src/test1.txt");

        // 2.读取文件中的字节，并输出
        // 定义一个变量记住读的一个字节
        int b;
        while ((b = is.read()) != -1){
            System.out.print((char) b);
        }
        // 每次读取一个字节的问题：性能较差，读取汉字输出一定会乱码
    }
}
