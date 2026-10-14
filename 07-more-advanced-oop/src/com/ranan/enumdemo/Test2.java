package com.ranan.enumdemo;

import java.util.zip.DeflaterInputStream;

public class Test2 {
    public static void main(String[] args) {
        // 枚举类应用场景
        // 1.常量方法
        move(2);
        // 2.枚举方法
        move2(Direction.LEFT);
    }

    public static void move(int direction){
        switch (direction){
            case Constant.UP:
                System.out.println("UP");
                break;
            case Constant.DOWN:
                System.out.println("DOWN");
                break;
            case Constant.LEFT:
                System.out.println("LEFT");
                break;
            case Constant.RIGHT:
                System.out.println("RIGHT");
                break;
            default:
                System.out.println("WRONG");
        }
    }

    public static void move2(Direction direction){
        switch (direction){
            case Direction.UP:
                System.out.println("UP");
                break;
            case Direction.DOWN:
                System.out.println("DOWN");
                break;
            case Direction.LEFT:
                System.out.println("LEFT");
                break;
            case Direction.RIGHT:
                System.out.println("RIGHT");
                break;
            default:
                System.out.println("WRONG");
        }
    }
}
