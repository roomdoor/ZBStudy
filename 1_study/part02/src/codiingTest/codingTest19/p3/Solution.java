package codiingTest.codingTest19.p3;


import java.util.Arrays;

public class Solution {
	public static int solution(int[] param0) {

		int max = Arrays.stream(param0).max().getAsInt();

		return max - param0.length;
	}

	public static void main(String[] args) {
		int[] a = new int[]{1, 5, 6, 4};

		System.out.println(solution(a));
	}
}