package com.ranan.practice;

public class Practice {
    public static void main(String[] args) {
        SilverCard sc = new SilverCard("10101","LiHua","1234567890",4000);
        sc.consume(2000);
        sc.consume(4000);
        sc.topUp(4000);
        sc.consume(4000);

        System.out.println("=================================================");

        GoldenCard gc = new GoldenCard("10101","LiHua","1234567890",7000);
        gc.consume(2000);
        gc.consume(200);
    }
}
