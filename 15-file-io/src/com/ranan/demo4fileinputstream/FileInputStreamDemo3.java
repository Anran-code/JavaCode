package com.ranan.demo4fileinputstream;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileInputStreamDemo3 {
    public static void main(String[] args) throws Exception{
        // 掌握文件字节输入流流读取文件中的字节数组到内存中来
        // 1.创建文件字节输入流管道于源文件接通
        InputStream is = new FileInputStream("15-file-io/src/test1.txt");

        // 2.一次性都全部字节
        byte[] bytes = is.readAllBytes();

        System.out.println(new String(bytes));
        // 文件过大超过内存就不适用了
    }
}
