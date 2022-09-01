package codiingTest.codingTest15.p3;


import java.util.Arrays;

public class Solution {
    public static int solution(int[] arr, int k) {
        int len = arr.length;
        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = arr[0];

        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                if (i + j >= len) {
                    break;
                }
                dp[i + j] = Math.max(dp[i + j], dp[i] + arr[i + j]);
            }
        }


        return dp[len - 1];
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, -4, 5, 1, 3, -5, -12, 4, -4, 5};
        System.out.println(solution(a, 3));

    }
}
