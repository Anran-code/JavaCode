package com.ranan.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionTravelDemo4 {
    public static void main(String[] args) {
        // 学习并发修改异常
        ArrayList<String> list = new ArrayList<>();
        list.add("Java入门");
        list.add("宁夏枸杞");
        list.add("黑枸杞");
        list.add("人字拖");
        list.add("特技枸杞");
        list.add("枸杞子");
        list.add("西洋参");

        // 需求1：删除全部枸杞
        // 问题：remove之后元素前移了，但是i还再往后走，导致删不干净
//        for(int i = 0;i < list.size();++i){
//            String s = list.get(i);
//            if(s.contains("枸杞")){
//                list.remove(i);
////                i--;  // 加上这个就对了
//            }
//        }
//        System.out.println(list);

        // 或者倒着遍历：
//        for(int i = list.size() - 1;i >= 0;--i){
//            String s = list.get(i);
//            if(s.contains("枸杞")){
//                list.remove(i);
//            }
//        }
//        System.out.println(list);

        // 采用迭代器删除，迭代器会自动检测到并发修改异常
        // 要使用迭代器自己的删除方法
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String s = it.next();
            if(s.contains("枸杞")){
                it.remove();
            }
        }
        System.out.println(list);

        // 增强for循环和Lambda表达式都完成不了删除
    }
}
