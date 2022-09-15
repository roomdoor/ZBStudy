package codiingTest.codingTest17.p4;


import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

	public static int solution(int dest, int start, int[] station, int[] fuel) {
		int answer = -1;
		int len = station.length;

		int all = start;

		for (int f : fuel) {
			all += f;
		}

		if (all < dest) {
			return -1;
		}

		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2] - o2[2] == 0) {
					return o2[1] - o1[1];
				} else {
					return o1[2] - o2[2];
				}
			}
		});
		queue.add(new int[]{0, start, 0, -1});

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curDis = cur[0];
			int curFuel = cur[1];
			int count = cur[2];
			int curPoint = cur[3];

			if (curDis + curFuel >= dest) {
				answer = count;
				break;
			}

			for (int i = curPoint + 1; i < len; i++) {
				if (curDis + curFuel < station[i]) {
					break;
				}

				queue.add(
					new int[]{station[i], curFuel - (station[i] - curDis) + fuel[i], count + 1, i});
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int dis = 12;
		int start = 6;
		int[] s = new int[]{2, 3, 5, 10};
		int[] f = new int[]{5, 2, 3, 1};

		System.out.println(solution(dis, start, s, f));
	}
}