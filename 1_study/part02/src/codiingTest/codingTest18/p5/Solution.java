package codiingTest.codingTest18.p5;


import java.util.Arrays;

public class Solution {

	public static int solution(int N, int[][] stones) {
		int answer = 0;
		int[] xx = new int[N];
		int[] yy = new int[N];

		for (int[] stone : stones) {
			int x = stone[0];
			int y = stone[1];

			xx[x]++;
			yy[y]++;
		}

		System.out.println(Arrays.toString(xx));
		System.out.println(Arrays.toString(yy));
		return answer;
	}

	public static void main(String[] args) {
		int N = 6;
		int[][] a = new int[][]{{0, 1}, {0, 2}, {5, 3}, {4, 3}};

		N = 6;
		a = new int[][]{
			{0, 1}, {0, 2}, {0, 3}, {0, 4},
			{1, 1}, {2, 2}, {3, 3}, {4, 4},};
		System.out.println(solution(N, a));
	}
}