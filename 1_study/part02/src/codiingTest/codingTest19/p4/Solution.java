package codiingTest.codingTest19.p4;


import java.util.HashSet;
import java.util.Set;

public class Solution {

	public int solution(int[] param0) {
		int answer = 0;
		Set<Integer> set = new HashSet<>();

		for (int a : param0) {
			if (set.contains(-1 * a)) {
				answer = Math.max(Math.abs(a), answer);
			}
			set.add(a);
		}

		return answer;
	}
}