package ch01.math06;
// Practice1
// 팩토리얼을 재귀함수로 구현하시오.

public class Practice1 {
     static int factorial(int num) {
        if (num == 1) {
            return num;
        }

        return num * factorial(num - 1);
    }

    public static void main(String[] args) {
//      Test code
        System.out.println(factorial(1));
        System.out.println(factorial(2));
        System.out.println(factorial(3));
        System.out.println(factorial(4));
        System.out.println(factorial(5));
    }
}

