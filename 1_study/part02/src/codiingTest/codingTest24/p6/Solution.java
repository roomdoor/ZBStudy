package codiingTest.codingTest24.p6;


import java.util.Map;

public class Solution {

	public int solution(int[] x, int[] y) {
		int answer = 0;
		int startY = 0;
		int endY = y.length - 1;
		int len = x.length;
		Point[] dp = new Point[len];
		dp[0] = new Point(0, 0, len - 1, x[0] * y[startY], x[0] * y[endY]);
		for (int i = 1; i < len; i++) {
			Point before = dp[i - 1];
			int startValue1 = x[i] * y[before.startY + 1] + before.value1;
			int endValue1 = x[i] * y[before.endY] + before.value1;
			int next1 = 0;
			int startValue2 = x[i] * y[before.startY] + before.value2;
			int endValue2 = x[i] * y[before.endY - 1] + before.value2;
			int next2 = 0;

			next1 = Math.max(startValue1, endValue1);

			next2 = Math.max(startValue2, endValue2);

			if (next1 > next2) {
				dp[i] = new Point(i, before.startY + 1, before.endY, startValue1, endValue1);
			} else {
				dp[i] = new Point(i, before.startY, before.endY - 1, startValue2, endValue2);
			}

		}

		return Math.max(dp[len - 1].value1, dp[len - 1].value2);
	}

	private static class Point {

		int x;
		int startY;
		int endY;
		int value1;
		int value2;

		public Point(int x, int startY, int endY, int value1, int value2) {
			this.x = x;
			this.startY = startY;
			this.endY = endY;
			this.value1 = value1;
			this.value2 = value2;
		}
	}
}