package codiingTest.codingTest17.p2;


import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

	public static int solution(int[][] value) {
		int len = value.length;
		boolean[] isUsed = new boolean[len];
		PriorityQueue<int[]> queue1 = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] - o2[1] == 0) {
					return o2[0] - o1[0];
				} else {
					return o2[1] - o1[1];
				}
			}
		});

		PriorityQueue<int[]> queue2 = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] - o2[1] == 0) {
					return o2[0] - o1[0];
				} else {
					return o2[1] - o1[1];
				}
			}
		});

		for (int i = 0; i < len; i++) {
			queue1.add(new int[]{value[i][0], value[i][0] + value[i][1], i});
			queue2.add(new int[]{value[i][1], value[i][0] + value[i][1], i});
		}

		int i = 0;
		int p1 = 0;
		int p2 = 0;
		while (!queue2.isEmpty()) {
			if (i++ % 2 == 0) {
				int[] cur = queue1.poll();
				while (isUsed[cur[2]]) {
					cur = queue1.poll();
				}
				p1 += cur[0];
				isUsed[cur[2]] = true;
			} else {
				int[] cur = queue2.poll();
				while (isUsed[cur[2]]) {
					cur = queue2.poll();
				}
				p2 += cur[0];
				isUsed[cur[2]] = true;
			}
		}
		System.out.println(p1);
		System.out.println(p2);


		return Integer.compare(p1, p2);
	}

	public static void main(String[] args) {
		int[][] a = new int[][]{{5, 3}, {6, 9}, {4, 5}, {6, 3}, {2, 8}, {5, 4}};
		System.out.println(solution(a));
	}
}