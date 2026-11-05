package com.ranan.linklist;

import java.util.LinkedList;

public class LinkListDemo2 {
    public static void main(String[] args) {
        //  用linklist实现栈
        LinkedList<String> queue = new LinkedList<>();
        // 入栈
        queue.addFirst("a");
        queue.addFirst("b");
        queue.push("c");
        queue.push("d"); // push套皮了addFirst
        System.out.println(queue);

         // 出栈
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
    }
}
