package com.ranan.demo6copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyDemo1 {
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

    // 复制文件（try-catch-finally）
    public static void copyFile(String srcPath ,String destPath) throws Exception {
        // 1.创建字节输入流管道与原文件接通
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcPath);
            fos = new FileOutputStream(destPath);
            // 2.读取一个字节数组，写入一个字节数组
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
            System.out.println("复制成功");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
