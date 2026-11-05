package com.ranan.linklist;

import java.util.LinkedList;

public class LinkListDemo1 {
    public static void main(String[] args) {
        //  用linklist实现队列
        LinkedList<String> queue = new LinkedList<>();
        // 入队
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        System.out.println(queue);

        // 出队
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
    }
}
