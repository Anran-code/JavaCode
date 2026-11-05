package com.ranan.test;

import java.util.*;

public class Room {
    // 1.准备好54张牌，给房间使用，定义容器
    private ArrayList<Card> cards = new ArrayList<>();

    // 2.实例代码块随对象创建初始化
    {
        // 3.准备点数
        String[] sizes = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        // 4.准备花色
        String[] colors = {"♥","♦","♣","♠"};
        // 5.自动组合成牌对象，加入容器
        for(String size :sizes){
            for(String color:colors){
                // 6.创建牌对象，加入容器
                cards.add(new Card(size,color));
            }
        }
        // 7.单独加入大小王
        cards.add(new Card("🃏",""));
        cards.add(new Card("👲",""));
    }
    public void start(){
        // 1.洗牌
        Collections.shuffle(cards);
        System.out.println(cards);

        // 2.发牌：定义三个玩家，A，B，C，用map去映射他们的手牌
        Map<String, ArrayList<Card>> players = new HashMap<>();
        ArrayList<Card> a = new ArrayList<>();
        players.put("A",a);

        ArrayList<Card> b = new ArrayList<>();
        players.put("B",b);

        ArrayList<Card> c = new ArrayList<>();
        players.put("C",c);

        // 实际上只发了51张
        // 采用轮询发牌
        for(int i = 0;i < cards.size() - 3;i++){
            if(i % 3 == 0){
                a.add(cards.get(i));
            } else if(i % 3 == 1){
                b.add(cards.get(i));
            } else {
                c.add(cards.get(i));
            }
        }

        // 3.确定底牌
        List<Card> lastCards = cards.subList(cards.size() - 3,cards.size());
        System.out.println("底牌：" + lastCards);

        // 4.抢地主（假设A）
        a.addAll(lastCards);

        // 5.对得到的手牌排序
        a.sort(new CardComparator());
        b.sort(new CardComparator());
        c.sort(new CardComparator());

        // 6.看牌：遍历三者手牌
        for(Map.Entry<String,ArrayList<Card>> entry : players.entrySet()){
            String name = entry.getKey();
            ArrayList<Card> cards = entry.getValue();
            System.out.println(name + cards);
        }
    }

}
