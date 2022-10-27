package codiingTest.codingTest23.p1;


public class Solution {

	public int solution(int L, int U, int D) {
		int answer = 0;
		int curHeight = 0;

		while (curHeight < L) {
			answer++;

			curHeight += U;

			if (curHeight < L) {
				curHeight -= D;
			}

		}

		return answer;
	}
}