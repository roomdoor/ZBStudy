package codiingTest.codingTest20.p2;


import java.util.HashMap;
import java.util.Map;

public class Solution {

	public int solution(int[][] x, int[][] y) {
		int answer = 0;

		Map<Integer, Integer> xMap = new HashMap<>();
		Map<Integer, Integer> yMap = new HashMap<>();
		for (int[] xx : x) {
			xMap.put(xx[0], xx[1]);
		}

		for (int[] yy : y) {
			yMap.put(yy[0], yy[1]);
		}

		for (int xKey : xMap.keySet()) {
			if (yMap.containsKey(xKey)) {
				answer += xMap.get(xKey) * yMap.get(xKey);
			}
		}

		return answer;
	}
}