package com.ranan.demo2threadapi;

public class ThreadAPIDemo2 {
    public static void main(String[] args) {
        // 学习线程的sleep方法
        for(int i = 0;i < 10; ++ i){
            System.out.println(i);
            try{
                Thread.sleep(1000); // 1s
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
