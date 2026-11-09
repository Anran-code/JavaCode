package com.ranan.demo4fileinputstream;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileInputStreamDemo2 {
    public static void main(String[] args) throws Exception {
        // 掌握文件字节输入流流读取文件中的字节数组到内存中来
        // 1.创建文件字节输入流管道于源文件接通
        InputStream is = new FileInputStream("15-file-io/src/test1.txt");

        // 2.读取文件中的字节，每次读取多个数组
        // 定义一个字节数组用于每次读字节
        byte[] buffer = new byte[3];
        // 定义一个变量记住每次读了几个字节
        int len;
        while((len = is.read(buffer)) != -1){
            // 读多少，转换多少
            String str = new String(buffer,0,len);
            System.out.println(str);
        }
        // 性能提升了，但是如果汉字字符被拆开了，还是乱码
        // 可以定义一个和文件一样大的字符数组，一口气全部读完
    }
}
