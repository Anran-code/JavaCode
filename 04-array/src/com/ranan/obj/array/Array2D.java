package com.ranan.obj.array;

public class Array2D {
    public static void main(String[] args){
        PrintArray2D();
    }

    public static void PrintArray2D(){
        String[][] names = {
                {"LiHua","LiMing","YongZhiming"},
                {"ZhangWei","MingMing","AnDi"}
        };
        for(int i = 0;i < names.length;++i){
            for(int j = 0;j < names[i].length;++j){
                System.out.printf("%-20s",names[i][j]);
            }
            System.out.println();
        }

        // 动态创建数组
        int[][] nums = new int[3][4];
        for(int i = 0;i < nums.length;++i){
            for(int j = 0;j < nums[i].length;++j){
                System.out.printf("%-20s",nums[i][j]);
            }
            System.out.println();
        }
    }
}
