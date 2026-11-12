package com.ranan.demo13printstream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataStreamDemo3 {
    public static void main(String[] args) {
        // 学习数据流
        try(
                DataInputStream dis = new DataInputStream(new FileInputStream("15-file-io/src/dos.txt"));
                ) {
            System.out.println(dis.readByte());
            System.out.println(dis.readChar());
            System.out.println(dis.readUTF());
            System.out.println(dis.readDouble());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
