package codiingTest.codingTest21.p5;


import java.util.Arrays;

public class Solution {

	public static int solution(int[][] boxes) {
		int len = boxes.length;
		int[] dp = new int[len];
		dp[0] = 1;

		Arrays.sort(boxes, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
		System.out.println(Arrays.deepToString(boxes));

		for (int i = 1; i < len; i++) {
			for (int j = i - 1; j >=0 ; j--) {
				if (boxes[i][0] > boxes[j][0] && boxes[i][1] > boxes[j][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		return Arrays.stream(dp).max().getAsInt();
	}


	public static void main(String[] args) {
		int[][] a = new int[][]{{5, 3}, {8, 6}, {1, 2}, {1, 5}, {3, 2}, {5, 5}, {12, 7}};

		System.out.println(solution(a));
	}
}