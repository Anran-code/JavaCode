package com.ranan.demo13printstream;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DataStreamDemo2 {
    public static void main(String[] args) {
        // 学习数据流
        try(
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("15-file-io/src/dos.txt"));
                ) {
            dos.writeByte(34);
            dos.writeChar(97);
            dos.writeUTF("你好");
            dos.writeDouble(98.8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
