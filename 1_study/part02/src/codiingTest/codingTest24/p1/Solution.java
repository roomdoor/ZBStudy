package codiingTest.codingTest24.p1;


public class Solution {
	public int solution(int[] damages, int shield) {
		int answer = 1;

		for (int i = 0; i < damages.length; i++) {
			damages[i] = Math.max(damages[i] - shield, 0);
			answer += damages[i];
		}


		return answer;
	}
}