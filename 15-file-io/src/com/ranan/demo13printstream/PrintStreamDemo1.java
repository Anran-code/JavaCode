package com.ranan.demo13printstream;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamDemo1 {
    public static void main(String[] args) {
        // 学习打印流
        try(
                PrintStream ps = new PrintStream("15-file-io/src/ps.txt");
                // PrintStream ps1 = new PrintStream(new FileOutputStream("15-file-io/src/ps.txt",true)); 若想追加
                ){
            ps.println(97);
            ps.println('a');
            ps.println(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
