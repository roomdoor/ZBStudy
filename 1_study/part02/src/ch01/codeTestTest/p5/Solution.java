package ch01.codeTestTest.p5;

public class Solution {
    public static int solution(int n, int a, int b) {
        int answer = 0;
        int LA = a <= n / 2 ? 0 : 1;
        int LB = b <= n / 2 ? 0 : 1;

        if (LA != LB) {
            while (n > 1) {
                n /= 2;
                answer++;
            }
        } else if (LA == 1) {
            answer = solution(n / 2, a - n / 2, b - n / 2);
        } else {
            answer = solution(n / 2, a, b);
        }

        return answer;
    }
}
