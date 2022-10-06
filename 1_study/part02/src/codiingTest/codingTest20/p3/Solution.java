package codiingTest.codingTest20.p3;


import java.util.Arrays;
import java.util.Comparator;

public class Solution {

	public static int solution(int[][] activity) {
		int answer = 0;
		boolean[] isVisited = new boolean[activity.length];
		Arrays.sort(activity, new Comparator<int[]>() {
			@Override
			public int compare(int[] a1, int[] a2) {
				if (a1[1] == a2[1]) {
					return a1[0] - a2[0];
				}
				return a1[1] - a2[1];
			}
		});

		int visitedCount = 0;
		while (visitedCount < activity.length) {
			int beforeEndTime = 0;
			answer++;
			for (int i = 0; i < activity.length; i++) {
				if (!isVisited[i] && beforeEndTime <= activity[i][0]) {
					visitedCount++;
					isVisited[i] = true;
					beforeEndTime = activity[i][1];
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) {

		int[][] a = new int[][]{{0, 5}, {2, 6}, {3, 5}, {7, 10}, {5, 9}, {13, 15}};
		System.out.println(solution(a));

		a = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {5, 6}, {6, 7}, {0, 10}};
		System.out.println(solution(a));

		a = new int[][]{{1, 10}, {2, 10}, {3, 10}, {5, 10}, {6, 10}, {0, 10}};
		System.out.println(solution(a));

		a = new int[][]{{1, 10}, {2, 10}, {3, 10}, {5, 10}, {6, 10}, {0, 10}};
		System.out.println(solution(a));
	}
}