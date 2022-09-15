package codiingTest.codingTest17.p1;

public class Solution {

	public static int solution(int[] nums) {
		int answer = 0;
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;

		for (int num : nums) {
			if (max1 < num) {
				max2 = max1;
				max1 = num;
			} else if (max1 == num) {
				max2 = max1;
			} else if (max2 < num) {
				max2 = num;
			}
		}

		answer = (max1 - 1) * (max2 - 1);

		return answer;
	}

	public static void main(String[] args) {
		int[] a = new int[]{3, 5, 7, 5};

		System.out.println(solution(a));
		System.out.println((-10) * (-10));
	}
}