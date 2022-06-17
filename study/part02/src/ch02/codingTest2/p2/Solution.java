package ch02.codingTest2.p2;

import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public static int[] solution(int[] a, int[] b) {
        int[] answer;

        Stack<Integer> stack = new Stack<>();
        int aLen = a.length;                // a 배열의 길이
        int bLen = b.length;                // b 배열의 길이
        int maxLen = Math.max(aLen, bLen);

        boolean carry = false;                              // a, b 의 자리수를 더했을 때 10보다 큰 수가 발생했다는 것을 알리는 boolean 변수
        for (int i = maxLen - 1; i >= 0 || carry; i--) {    // 배열읠 맨 끝자리가 1의 자리수 이기 떄문에 배열 끝자리부터 시작하고
            int numA = 0;                                   // 배열이 끝나거나 carry 가 없을떄 까지 반복문을 실행
            int numB = 0;
            int result;

            if (carry) {                                    // carry 가 있는 경우 a, b 각 자리를 더하고 1을 추가로 더해줌
                if (isPossible(aLen, i - (maxLen - aLen))) {              // isPossible 함수는 for 문 의 i 값이 배열 범위에 있는지 체크하기 위함
                    numA = a[i - (maxLen - aLen)];
                }

                if (isPossible(bLen, i - (maxLen - bLen))) {  // b 배열은 a 배열보다 짧기 때문에 1의자리 index = i - (a.length - b.length)
                    numB = b[i - (maxLen - bLen)];
                }

                result = numA + numB + 1;               // 자리수 더하는 곳
                carry = result >= 10;                   // carry 확인
            } else {                                    // carry 가 없는 경우 a, b 각 자리만 더해줌
                if (isPossible(aLen, i - (maxLen - aLen))) {
                    numA = a[i - (maxLen - aLen)];
                }
                if (isPossible(bLen, i - (maxLen - bLen))) {
                    numB = b[i - (maxLen - bLen)];
                }

                result = numA + numB;                   // 자리수 더하는 곳
                carry = result >= 10;                   // carry 확인
            }

            stack.push(result % 10);              // 나온 값들을 stack 에 넣어 1의 자리수 부터 먼저 넣음
        }                                              // 가장 큰 자리수가 가장 늦게 들어가서
        // 하니싹 꺼내면서 배열에 저장하면 순서가 맞음
        answer = new int[stack.size()];

        for (int i = 0; i < answer.length; i++) {       // 출력할 배열에 stack 의 값을 하나씩 빼면서 저장
            answer[i] = stack.pop();
        }

        return answer;
    }

    public static boolean isPossible(int length, int num) {
        return num < length && num >= 0;
    }

    public static void main(String[] args) {

        int[] a = {9};
        int[] b = {1, 1};

        System.out.println(Arrays.toString(solution(a, b)));

    }
}
