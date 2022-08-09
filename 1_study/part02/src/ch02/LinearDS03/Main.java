package ch02.LinearDS03;

import java.util.List;

class Node {
    int data;
    Node next;

    Node() {
    }

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

class LinkedList {
    Node head;

    LinkedList() {
    }

    LinkedList(Node node) {
        this.head = node;
    }

    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        }
        return false;
    }

    public void addData(int data) {
        if (this.head == null) {
            this.head = new Node(data, null);
        } else {
            Node cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Node(data, null);
        }
    }

    public void removeData() {
        if (this.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        Node cur = this.head;
        Node prev = cur;

        while (cur.next != null) {
            prev = cur;
            cur = cur.next;
        }

        if (cur == this.head) {
            this.head = null;
        } else {
            prev.next = null;
        }
    }

    public void findData(int data) {
        if (this.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        Node cur = this.head;
        while (cur != null) {
            if (cur.data == data) {
                System.out.println("data exist");
                return;
            }
            cur = cur.next;
        }
        System.out.println("Data not fount!");
    }

    public void showData() {
        if (this.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

}


public class Main {
    public static void main(String[] args) {
//      Test Code
        LinkedList myList = new LinkedList(new Node(1, null));
        myList.showData();      // 1

        myList.addData(2);
        myList.addData(3);
        myList.addData(4);
        myList.addData(5);
        myList.showData();      // 1 2 3 4 5

        myList.findData(3);     // Data exist!
        myList.findData(100);   // Data not found!

        myList.removeData();
        myList.removeData();
        myList.removeData();
        myList.showData();      // 1 2

        myList.removeData();
        myList.removeData();
        myList.removeData();    // List is empty


    }
}
