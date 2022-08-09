package ch01.math09_2;

import java.util.Arrays;

public class Practice3 {
    public static String solution(String equation) {
        String[] str = equation.split("=");
        int[] left = evaluate(str[0]);
        int[] right = evaluate(str[1]);

        int x = left[0] - right[0];
        int num = right[1] - left[1];

//        System.out.println(Arrays.toString(left));
//        System.out.println(Arrays.toString(right));

        if (x == 0) {
            return "Infinite solutions";
        } else if (num == 0) {
            return "x=0";
        }

        x = num / x;

        return "x=" + x;
    }

    public static int[] evaluate(String str) {
        int[] result = new int[2];
        boolean isMinus = false;
        int beforeNum = 1;

        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            switch (s) {
                case '+':
                    beforeNum = 1;
                    break;

                case '-':
                    isMinus = true;
                    break;

                case 'x':
                    if (beforeNum != 1) {
                        result[1] -= beforeNum;
                    }
                    result[0] += isMinus ? -1 * beforeNum : beforeNum;
                    isMinus = false;
                    beforeNum = 1;
                    break;

                default:
                    beforeNum = isMinus ? '0' - s : s - '0';
                    result[1] += beforeNum;
                    isMinus = false;
                    break;
            }
        }

        return result;
    }

    // # 2 정규표현식 사용
    public static int[] evaluate2(String str) {
        return null;
    }

    public static void main(String[] args) {
        // Test code
        String equation = "x+5-3+x=6+x-2";
        System.out.println(solution(equation));


        equation = "x=x";
        System.out.println(solution(equation));


        equation = "2x=x";
        System.out.println(solution(equation));
    }
}
