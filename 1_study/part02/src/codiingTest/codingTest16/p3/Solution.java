package codiingTest.codingTest16.p3;


import java.util.Arrays;
import java.util.Comparator;

public class Solution {

	public static int solution(int[] nums) {
		int answer = 0;
		int len = nums.length;
		int[][] arr = new int[len][2];

		for (int i = 0; i < len; i++) {
			arr[i][0] = nums[i];
			arr[i][1] = digitSum(nums[i]);
		}

		Arrays.sort(arr, new Comparator<>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] - o2[1] == 0) {
					return o2[0] - o1[0];
				} else {
					return o2[1] - o1[1];
				}
			}
		});

		int digitSumNum = arr[0][1];
		int beforeNum = arr[0][0];
		for (int i = 1; i < len; i++) {
			if (digitSumNum == arr[i][1]) {
				answer = Math.max(answer, beforeNum + arr[i][0]);
			} else {
				beforeNum = arr[i][0];
				digitSumNum = arr[i][1];
			}
		}

		System.out.println(Arrays.deepToString(arr));

		return answer;
	}

	public static int digitSum(int num) {
		int answer = 0;
		while (num > 0) {
			answer += num % 10;
			num /= 10;
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{18, 36, 72, 16, 52};
		System.out.println(solution(nums));
	}
}
