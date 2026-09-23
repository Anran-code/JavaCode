package com.ranan.obj.practice;


public class PrimeNumberPrint {
    public static void main(String[] args){
        System.out.println("=========素数输出（101-200）=========");
        pnPrint();
        System.out.println("==========================");
    }

    public static boolean checkPrime(int num){
        for(int i = 3;i <= (int)Math.sqrt(num);i += 2){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void pnPrint(){
        int count = 0;
        for(int num = 101;num <= 200;++num){
            if(num % 2 == 0) continue;
            if(checkPrime(num)){
                count++;
                System.out.printf("%-4d",num);
            }
        }
        System.out.printf("\n共 %d 个\n",count);
    }
}
