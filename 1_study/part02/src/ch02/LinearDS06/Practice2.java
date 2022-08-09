package ch02.LinearDS06;// Practice2
// 배열을 이용한 기본 스택 직접 구현

class MyStack2 {
    int[] arr;
    int top = -1;

    MyStack2(int size) {
        arr = new int[size];
    }

    public boolean isEmpty() {
        if (this.top == -1) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (this.top == arr.length - 1) {
            return true;
        }
        return false;
    }

    public void push(int data) {
        if (isFull()) {
            System.out.println("Stack is full");
            return;
        }

        this.top++;
        this.arr[this.top] = data;
    }

    public Integer pop() {
        if (this.isEmpty()) {
            System.out.println("Stakc is empty");
            return null;
        }

        int data = this.arr[top];
        this.top--;
        return data;
    }

    public Integer peek() {
        if (this.isEmpty()) {
            System.out.println("Stakc is empty");
            return null;
        }

        return this.arr[top];
    }

    public void printStack() {
        for (int i = 0; i < this.top; i++) {
            System.out.print(this.arr[i] + " ");
        }
    }
}

public class Practice2 {
    public static void main(String[] args) {
        // Test code
        MyStack2 myStack = new MyStack2(5);
        System.out.println(myStack.isEmpty());
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.printStack();               // 1, 2, 3, 4, 5

        System.out.println(myStack.peek()); // 5
        myStack.printStack();               // 1, 2, 3, 4, 5

        System.out.println(myStack.pop());  // 5
        System.out.println(myStack.pop());  // 4
        myStack.printStack();               // 1, 2, 3

        System.out.println(myStack.pop());  // 3
        System.out.println(myStack.pop());  // 2
        System.out.println(myStack.pop());  // 1
        myStack.printStack();
    }
}
