package com.ranan.demo5fileoutput;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileOutputStreamDemo1 {
    public static void main(String[] args) throws Exception{
        // 学习文件字节输出流
        // 1.创建管道沟通目标文件
        OutputStream os = new FileOutputStream("15-file-io/src/test2.txt"); // 第二个参数boolean append表示是否可追加

        // 2.写入数据
        os.write(97);
        os.write('b');

        byte[] bytes = "我爱你666".getBytes();
        os.write(bytes); // 写入字节数组
        os.write("\r\n".getBytes());

        os.write(bytes,0,3); // 写入字节数组的一部分
        os.write("\r\n".getBytes());

        os.close();
    }
}
