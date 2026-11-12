package com.ranan.demo9bufferedinputstream;

import java.io.*;

public class CopyDemo1 {
    public static void main(String[] args) {
        // 目标：学习缓冲字节流
        // 源文件： C:\Users\A\Pictures\Saved Pictures\IMG_0007.png
        // 目的地： C:\Users\A\Desktop\IMG_0007.png (必须带文件名)
        try {
            copyFile("C:\\Users\\A\\Pictures\\Saved Pictures\\IMG_0007.png",
                    "C:\\Users\\A\\Desktop\\IMG_0007.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 复制文件（try-with-resourse）
    public static void copyFile(String srcPath ,String destPath) throws Exception {
        // 1.创建字节输入流管道与原文件接通
        try (
                FileInputStream fis = new FileInputStream(srcPath);
                // 把低级的字节输入流包装成高级的缓冲字节输入流
                InputStream bis = new BufferedInputStream(fis);

                FileOutputStream fos = new FileOutputStream(destPath);
                // 把低级的字节输出流包装成高级的缓冲字节输出流
                OutputStream bos = new BufferedOutputStream(fos);
        ) {
            // 2.读取一个字节数组，写入一个字节数组
            byte[] buffer = new byte[1024];
            int len;
            int i = 0;
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);
                ++i;
            }
            System.out.println("复制成功，操作了：" + i + "次");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

