package codiingTest.codingTest24.p7;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static int[] cards;
	public static List<int[]> points;
	public static Boolean[] dp;


	public static boolean solution(int[] cards) {
		Solution.cards = cards;
		boolean answer = true;
		points = new ArrayList<>();
		int len = cards.length;
		dp = new Boolean[100];
		dp[0] = false;
		dp[1] = false;
		dp[2] = true;
		for (int i = 1; i < len; i++) {
			int first = i - 1;
			if (cards[first] == 1) {
				while (i < len && cards[i] == 1) {
					i++;
				}
				points.add(new int[]{first, i - 1});
			}
		}
		int count = 0;

		for (int[] point : points) {
			int num = point[1] - point[0] + 1;
			if (DP(num)) {
				count++;
			}
		}
		if (count == 0) {
			return false;
		}
		return count % 2 != 0;
	}

	public static boolean DP(int num) {
		if (dp[num] != null) {
			return dp[num];
		} else {
			for (int i = 2; i <= (num + 2) / 2; i++) {
				if (!DP(num - i)) {
					dp[num] = true;
					return dp[num];
				}
			}
			return dp[num] = false;
		}
	}

	public static void main(String[] args) {
		int[] a = new int[]{1, 1, 0, 1, 1};
//		System.out.println(solution(a));

		a = new int[]{1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1,};
//		System.out.println(solution(a));

		a = new int[]{1, 1, 1};
		System.out.println(solution(a));
//
		a = new int[]{1, 1, 1, 1};
		System.out.println(solution(a));
//
		a = new int[]{1, 1, 1, 1, 1};
		System.out.println(solution(a));
//
		a = new int[]{1, 1, 1, 1, 1, 1, 1};
		System.out.println(solution(a));
	}

}
//	0	x
//	1	x
//	2	q
//	3	q
//	4	q
//	5	x
//	6	x
//	7	q
//	8
//	9
// 10
// 11
// 12
// 13
// 14
// 15
// 16
// 17
// 18
// 19
// 20
//


