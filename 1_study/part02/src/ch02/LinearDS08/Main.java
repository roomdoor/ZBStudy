package ch02.LinearDS08;// 선형 자료구조 - 큐


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue queue = new LinkedList();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        System.out.println(queue);

        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);

        System.out.println(queue.peek());
        System.out.println(queue);
    }
}
