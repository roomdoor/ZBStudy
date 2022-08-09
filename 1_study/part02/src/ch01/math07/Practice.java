package ch01.math07;
// Practice
// 제곱과 제곱근을 Math 없이 구현하기

public class Practice {

    static double pow(double a, double b) {
        double result = 1;

        if (b >= 0) {
            for (int i = 0; i < b; i++) {
                result *= a;
            }
            return result;
        } else {
            b *= -1;
            for (int i = 0; i < b; i++) {
                result *= a;
            }
            return 1 / result;
        }


    }

    public static void main(String[] args) {

//      Test code
        System.out.println("== Math pow ==");
        System.out.println(Math.pow(2, 3));
        System.out.println(Math.pow(2, -3));
        System.out.println(Math.pow(-2, -3));

        System.out.println("== My pow ==");
        System.out.println(pow(2, 3));
        System.out.println(pow(2, -3));
        System.out.println(pow(-2, -3));

        System.out.println("== Math sqrt ==");
        System.out.println(Math.sqrt(16));
        System.out.println(Math.sqrt(8));

        System.out.println("== My sqrt ==");
//        System.out.println(sqrt(16));
//        System.out.println(sqrt(8));

    }
}

