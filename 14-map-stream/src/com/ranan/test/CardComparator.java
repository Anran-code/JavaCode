package com.ranan.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// 手牌大小的比较器
public class CardComparator implements Comparator<Card> {
    private static final ArrayList<String> sizes = new ArrayList<>();
    private static final ArrayList<String> colors = new ArrayList<>();

    static {
        Collections.addAll(sizes,new String[]{"3","4","5","6","7","8","9","10","J","Q","K","A","2","🃏","👲"});
        Collections.addAll(colors,new String[]{"♥","♦","♣","♠"});
    }

    @Override
    public int compare(Card o1, Card o2) {
        // 数字不一样，返回数字大的大
        if(sizes.indexOf(o1.getSize()) != sizes.indexOf(o2.getSize())){
            return Integer.compare(sizes.indexOf(o1.getSize()),sizes.indexOf(o2.getSize()));
        } else { // 数字一样大，返回花色大的大
            return Integer.compare(colors.indexOf(o1.getColor()),colors.indexOf(o2.getColor()));
        }
    }
}
