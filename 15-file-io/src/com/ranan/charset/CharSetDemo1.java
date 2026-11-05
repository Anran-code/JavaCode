package com.ranan.charset;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CharSetDemo1 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 学习字符的解码和编码
        // 1.编码
        String name = "我爱你中国aaabbb123123";

        //byte[] bytes = name.getBytes(); // StandardCharsets.UTF_8
        byte[] bytes = name.getBytes("GBK");
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));

        // 2.解码
        // String name2 = new String(bytes); // 默认UTF-8
        String name2 = new String(bytes,"GBK");
        System.out.println(name2);
    }
}
