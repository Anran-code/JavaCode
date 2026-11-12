package com.ranan.demo14commonsioframe;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CommonsIoDemo1 {
    public static void main(String[] args) {
        // 学习IO框架
        try {
            FileUtils.copyFile(new File("15-file-io/src/practice.txt"),new File("15-file-io/src/practice1.txt"));
            FileUtils.delete(new File("C:\\Users\\A\\Desktop\\屏幕截图 2026-11-11 005138.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
