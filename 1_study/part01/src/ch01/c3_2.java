package ch01;

public class c3_2 {
    public static void main(String[] args) {

//      1. 비트 논리 연산자
        System.out.println("== 비트 논리 연산자 ==");
        int num1 = 5;
        int num2 = 3;
        int result = 0;

        result = num1 & num2;
        System.out.println("result = " + result);
        System.out.println(Integer.toBinaryString(result));
        System.out.printf("%04d\n", Integer.parseInt(Integer.toBinaryString(num1)));
        System.out.printf("%04d\n", Integer.parseInt(Integer.toBinaryString(num2)));
        System.out.printf("%04d\n", Integer.parseInt(Integer.toBinaryString(result)));

//      1-1. AND 연산자 (&)


//      1-2. OR 연산자 (|)


//      1-3. XOR 연산자 (^)


//      1-4. 반전 연산자 (~)
        num1 = 5;
        result = ~num1;
        System.out.println("result = " + result);
        System.out.printf("%04d\n", Integer.parseInt(Integer.toBinaryString(num1)));
        System.out.printf("%s\n", (Integer.toBinaryString(result)));

//      2. 비트 이동 연산자
        System.out.println("== 비트 이동 연산자 ==");
//      2-1. << 연산자


//      2-2. >> 연산자


//      2-3. >>> 연산자


    }
}
