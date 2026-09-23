package com.ranan.obj.loop;

public class NestedLoop {
    public static void main(String[] args){
        PrintMultiplicationTable();
        System.out.println("=======================================================================================");
        PrintPyramid(15);
    }

    // 1.打印九九乘法表
    public static void PrintMultiplicationTable(){
        for(int i = 1;i <= 9;++i){
            for(int j = 1;j <= i;j++){
                System.out.printf("%d*%d=%-4d",i,j,i*j);
            }
            System.out.println();
        }
    }

    // 2.打印金字塔
    public static void PrintPyramid(int layer){
        for(int i = 0;i < layer;++i){
            for(int j = 0;j < layer - 1 - i;++j){
                System.out.printf(" ");
            }
            for(int j = 0;j < 2 * i + 1;++j){
                System.out.printf("*");
            }
            for(int j = 0;j < layer - 1 - i;++j){
                System.out.printf(" ");
            }
            System.out.println();
        }
    }
}
