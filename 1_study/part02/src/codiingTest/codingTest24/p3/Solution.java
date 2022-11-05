package codiingTest.codingTest24.p3;


import java.util.Arrays;

public class Solution {

	public static int[] solution(int[] nums) {
		boolean isChanged = true;
		int len = nums.length;
		int[] addArr;
		while (isChanged) {
			isChanged = false;
			addArr = new int[len];
			for (int i = 1; i < len - 1; i++) {
				if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
					addArr[i]--;
					isChanged = true;
				}

				if (nums[i] < nums[i - 1] && nums[i] < nums[i + 1]) {
					addArr[i]++;
					isChanged = true;
				}
			}

			if (isChanged) {
				for (int i = 0; i < len; i++) {
					nums[i] += addArr[i];
				}
			}
		}

		return nums;
	}

	public static void main(String[] args) {
		int[] a = new int[]{10, 8, 6, 7, 5};
		System.out.println(Arrays.toString(solution(a)));
		a = new int[]{74, 24, 69, 45, 59, 6, 84, 64, 27, 77};
		System.out.println(Arrays.toString(solution(a)));
	}

}