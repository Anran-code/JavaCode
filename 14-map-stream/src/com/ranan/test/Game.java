package com.ranan.test;

public class Game {
    public static void main(String[] args) {
        // 开发斗地主游戏
        // 1.每张牌都是一个对象，定义牌类
        // 2.游戏房间也是一个类，定义包含54张牌的房间类，开始游戏
        Room r = new Room();
        r.start();
    }
}
