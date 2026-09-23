package com.ranan.obj.array;

public class HuaRongRoadDemo {
    public static void main(String[] args){
        gameStart(5);
    }

    public static void printArray(int[][] arr){
        for(int i = 0;i < arr.length;++i){
            for(int j = 0;j < arr[i].length;++j){
                System.out.printf("%-3d",arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void gameStart(int n){
        int[][] arr = new int[n][n];
        // 初始化
        for(int i = 0;i < n;++i){
            for(int j = 0;j < n;++j){
                arr[i][j] = i * n + j + 1;
            }
        }
        printArray(arr);

        System.out.println("===================================");

        // 打乱
        int times = n * n;
        for(int i = 0;i < times;++i){
            int index1_x = (int)(Math.random() * 5);
            int index1_y = (int)(Math.random() * 5);
            int index2_x = (int)(Math.random() * 5);
            int index2_y = (int)(Math.random() * 5);

            int temp = arr[index1_x][index1_y];
            arr[index1_x][index1_y] = arr[index2_x][index2_y];
            arr[index2_x][index2_y] = temp;
        }
        printArray(arr);
    }
}
