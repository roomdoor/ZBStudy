package codiingTest.codingTest18.p1;

import java.util.PriorityQueue;

public class Solution {

	public int solution(int[] food) {
		int answer = 0;

		PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
		for (int f : food) {
			queue.add(f);
		}

		while (!queue.isEmpty()) {
			int curHead1 = queue.poll();

			if (queue.isEmpty()) {
				return answer + curHead1;
			}

			int curHead2 = queue.poll();

			answer += curHead1 - (curHead1 - curHead2);

			curHead1 -= curHead2;
			curHead2 = 0;

			if (curHead1 >= 0) {
				queue.add(curHead1);
			}
		}

		return answer;
	}
}