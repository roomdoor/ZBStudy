package ch02.LinearDS07;// Practice3
// 후위표기법 연산
// 참고 설명) 전위/중위/후위 표기법

// 입출력 예시)
// 입력: "2 2 +"
// 출력: 4

// 입력: "2 2 -"
// 출력: 0

import java.util.Stack;

public class Practice3 {
    public static double calculate(String string) {
        Stack<Double> stack = new Stack<>();

        for (String s : string.split(" ")) {
            double a = 0;
            double b = 0;
            switch (s) {
                case "+":
                    a = stack.pop();
                    b = stack.pop();

                    stack.push(a + b);
                    break;
                case "-":
                    a = stack.pop();
                    b = stack.pop();

                    stack.push(-a + b);
                    break;
                case "*":
                    a = stack.pop();
                    b = stack.pop();

                    stack.push(a * b);
                    break;
                case "/":
                    a = stack.pop();
                    b = stack.pop();

                    stack.push(b / a);
                    break;

                default:
                    stack.push(Double.valueOf(s));
                    break;
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(calculate("2 2 +"));    // 4
        System.out.println(calculate("2 2 -"));    // 0
        System.out.println(calculate("2 2 *"));    // 4
        System.out.println(calculate("2 2 /"));    // 1

        System.out.println(calculate("1 1 + 2 * 3 * 2 / 5 -"));    // 1
        System.out.println(calculate("5 2 * 3 - 8 * 4 /"));        // 14
//        String a = "1";
//        String b = "2";
//        System.out.println(a);
//        System.out.println(b);

    }
}
