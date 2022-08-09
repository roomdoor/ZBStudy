package codiingTest.codingTest8.p1;

import java.util.Arrays;

public class Solution {
    private static class Consulting {
        int start;
        int end;
        int price;

        public Consulting(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }
    }

    public static int solution(int[] start, int[] end, int[] price) {
        int answer = 0;
        int len = start.length;
        int maxTime = 0;
        Consulting[] consultings = new Consulting[len];
        for (int i = 0; i < len; i++) {
            consultings[i] = new Consulting(start[i], end[i], price[i]);
            maxTime = Math.max(maxTime, end[i]);
        }

        Arrays.sort(consultings, (x, y) -> x.end - y.end);

        int[] dp = new int[maxTime + 1];

        for (Consulting consulting : consultings) {
            for (int i = consulting.end; i < dp.length; i++) {
                dp[i] = Math.max(dp[consulting.start] + consulting.price, dp[i]);
            }
        }


        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 5, 10, 6, 5};
        int[] b = new int[]{5, 6, 12, 9, 12};
        int[] c = new int[]{10, 40, 30, 20, 50};
        System.out.println(solution(a, b, c));

        a = new int[]{1, 2, 5, 1, 4, 11};
        b = new int[]{10, 9, 6, 3, 9, 15};
        c = new int[]{50, 20, 50, 20, 80, 40};
        System.out.println(solution(a, b, c));
    }
}