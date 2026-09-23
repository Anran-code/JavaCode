package com.ranan.obj.array;

public class Poker {
    public static void main(String[] args){
        start();
    }

    public static void start(){
        String[] pokers = new String[54];

        String[] colors = {"♠","♥","♣","♦"};
        String[] nums = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};

        int index = 0;
        for(int i = 0;i < nums.length;++i){
            for(int j = 0;j < colors.length;++j){
                pokers[index++] = nums[i] + colors[j];
            }
        }

        pokers[index++] = "🃏";
        pokers[index++] = "🃏";

        for(int i = 0;i < pokers.length;++i){
            System.out.printf("%s ",pokers[i]);
        }

        int times = 54;

        for(int i = 0;i < times;++i){
            int index1 = (int)(Math.random() * 54);
            int index2 = (int)(Math.random() * 54);

            String temp = pokers[index1];
            pokers[index1] = pokers[index2];
            pokers[index2] = temp;
        }

        System.out.println();
        for(int i = 0;i < pokers.length;++i){
            System.out.printf("%s ",pokers[i]);
        }
    }
}
