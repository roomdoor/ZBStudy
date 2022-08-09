package ch04.Algorithm_12;// 알고리즘 - 다이나믹 프로그래밍

public class Main {

    // 피보나치 수열 (일반풀이 - O(n^2))
    // 계산 했던 부분도 다시 계산
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }

    // 피보나치 수열 (DP 풀이 - 타뷸레이션 - O(n))
    public static int fibDP(int n) {
        int[] dp = new int[n < 2 ? 2 : n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 피보나치 수열 (DP 풀이 - 메모이제이션 - O(n))
    public static int fibDP2(int n, int[] dp) {
        if (n <= 1) {
            return dp[n];
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        return dp[n] = fibDP2(n - 1, dp) + fibDP2(n - 2, dp);
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(fib(7));
        System.out.println(fibDP(7));
        int n = 7;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        System.out.println(fibDP2(n, dp));
    }
}
