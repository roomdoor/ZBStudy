package codiingTest.codingTest18.p2;


public class Solution {

	public static int solution(int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		int i = 1;
		int m2Index = 0;
		int m3Index = 0;
		int m5Index = 0;

		while (i < n) {
			int mul2 = dp[m2Index] * 2;
			int mul3 = dp[m3Index] * 3;
			int mul5 = dp[m5Index] * 5;

			int min = Math.min(mul2, Math.min(mul3, mul5));
			dp[i++] = min;

			if (mul2 == min) {
				m2Index++;
			}

			if (mul3 == min) {
				m3Index++;
			}

			if (mul5 == min) {
				m5Index++;
			}
		}

		return dp[n - 1];
	}

	public static void main(String[] args) {
//		System.out.println(solution(5));
		System.out.println(solution(1000));


	}
}