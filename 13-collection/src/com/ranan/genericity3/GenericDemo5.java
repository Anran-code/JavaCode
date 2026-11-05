package com.ranan.genericity3;

import java.util.ArrayList;

public class GenericDemo5 {
    public static void main(String[] args) {
        // 目标：学习通配符和上下限
        ArrayList<Xiaomi> xiaomis = new ArrayList<>();
        xiaomis.add(new Xiaomi());
        xiaomis.add(new Xiaomi());
        xiaomis.add(new Xiaomi());
//        go(xiaomis);  // 报错
        go1(xiaomis);
    }

    // 车比赛
    // 虽然Xiaomi和LX是Car的子类，但是ArrayList<Xiaomi>，ArrayList<LX>和ArrayList<Car>没有关系，这不是多态
//    public static void go(ArrayList<Car> cars){
//    }

    // 所以引入通配符
    // 但此时谁都可以传进来了（比如说Dog），故还要引入上下限，去限制传参
    // 泛型上限 ? extends Car : 表示接受的必须是Car或者其子类
    // 泛型下限 ? super Car : 表示接受的必须是Car或者其父类
    public static void go1(ArrayList<? extends Car> cars){
    }
}
