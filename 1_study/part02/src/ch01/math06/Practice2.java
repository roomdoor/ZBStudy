package ch01.math06;

// Practice2
// 최대공약수를 재귀함수로 구현하시오.

public class Practice2 {
    static int gcd(int numA, int numB) {
        if (numA % numB == 0) {
            return numB;
        } else {
            return gcd(numB, numA % numB);
        }
    }


    public static void main(String[] args) {
//      Test code
        System.out.println(gcd(3, 5));
        System.out.println(gcd(2, 6));
        System.out.println(gcd(8, 12));
    }
}
