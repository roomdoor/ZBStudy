package codiingTest.codingTest16.p5;


public class Solution {

	public int solution(int[][] heights) {
		int answer = 0;
		int xlen = heights.length;
		int ylen = heights[0].length;

		for (int i = 0; i < ylen; i++) {
			for (int j = 0; j < xlen / 2; j++) {

				int top = heights[j][i];
				int bottom = heights[xlen - 1 - j][i];


			}
		}

		for (int i = 0; i < ylen; i++) {

		}
		return answer;
	}
}

// [3, 3, 3, 3, 3, 3]
// [3, 1, 3, 1, 1, 3]
// [3, 1, 3, 2, 1, 3]
// [3, 3, 3, 3, 3, 3]

// [0, 0, 0, 0, 0, 0]
// [0, 2, 0, 2, 2, 0]
// [0, 2, 0, 1, 2, 0]
// [0, 0, 0, 0, 0, 0]


