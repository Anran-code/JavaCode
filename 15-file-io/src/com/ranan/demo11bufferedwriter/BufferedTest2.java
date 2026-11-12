package com.ranan.demo11bufferedwriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class BufferedTest2 {
    public static void main(String[] args) {
        // 乱序文本重排输出案例
        // 1.缓冲字符流获取文本
        try(
                BufferedReader br = new BufferedReader(new FileReader("15-file-io/src/practice.txt"));
                BufferedWriter bw = new BufferedWriter(new FileWriter("15-file-io/src/out.txt"))
                ) {
            // 2.读取句子放入容器
            List<String> list = new ArrayList<>();
            String str;
            while((str = br.readLine()) != null){
                list.add(str);
            }

            // 3.排序
            list.sort(((o1, o2) -> Character.compare(o1.charAt(0),o2.charAt(0)))); // 其实默认能排序，这里回顾一下

            // 4.输出
            for(String s : list){
                bw.write(s);
                bw.write("\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
