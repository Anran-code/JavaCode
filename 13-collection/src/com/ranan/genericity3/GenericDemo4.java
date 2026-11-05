package com.ranan.genericity3;

public class GenericDemo4 {
    public static void main(String[] args) {
        // 目标：学会泛型方法
        String[] names = {"Anran","Bobo","Tina","Zhao"};
        printArray(names);
        String max = getMax(names);
    }

    public static <T> void printArray(T[] names){
    }

    // 返回 T 类型，避免强制转换
    public static <T> T getMax(T[] arr){
        return null;
    }
}
