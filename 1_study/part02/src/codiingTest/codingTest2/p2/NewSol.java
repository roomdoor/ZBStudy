package codiingTest.codingTest2.p2;

import java.util.Stack;

public class NewSol {
    public static int[] solution(int[] a, int[] b) {
        int[] answer;
        if (a.length == 0) {
            return b;
        }
        if (b.length == 0) {
            return a;
        }

        Stack<Integer> stack = new Stack<>();
        int aLen = a.length;
        int bLen = b.length;
        int maxLen = Math.max(aLen, bLen);

        int carry = 0;
        for (int i = maxLen - 1; i >= 0 || carry != 0; i--) {
            int numA = isPossible(aLen, i - (maxLen - aLen)) ? a[i - (maxLen - aLen)] : 0;
            int numB = isPossible(bLen, i - (maxLen - bLen)) ? b[i - (maxLen - bLen)] : 0;
            int result = numA + numB + carry;
            carry = result >= 10 ? 1 : 0;

            stack.push(result % 10);

        }

        answer = new int[stack.size()];

        for (int i = 0; i < answer.length; i++) {       // 출력할 배열에 stack 의 값을 하나씩 빼면서 저장
            answer[i] = stack.pop();
        }

        return answer;
    }

    public static boolean isPossible(int length, int num) {
        return num < length && num >= 0;
    }

}
