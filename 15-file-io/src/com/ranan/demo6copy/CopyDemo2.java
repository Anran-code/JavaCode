package com.ranan.demo6copy;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyDemo2 {
    public static void main(String[] args) {
        // 目标：使用字节流完成文件复制
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
                FileOutputStream fos = new FileOutputStream(destPath);
                Myclose mc = new Myclose();
        ) {
            // 2.读取一个字节数组，写入一个字节数组
            byte[] buffer = new byte[1024];
            int len;
            int i = 0;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
                ++i;
            }
            System.out.println("复制成功，操作了：" + i + "次");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Myclose implements Closeable{
    @Override
    public void close() throws IOException {
        System.out.println("close!");
    }
}
