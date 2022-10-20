package codiingTest.codingTest22.p1;


import java.util.Arrays;

public class Solution {

	public int solution(int[] nums) {
		int len = nums.length;
		int[] dp = new int[len];
		Arrays.fill(dp, 1);
		int answer = dp[0];

		for (int i = 1; i < len; i++) {
			for (int j = i; j >= 0; j--) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
			answer = Math.max(dp[i], answer);
		}

		return answer;
	}
}