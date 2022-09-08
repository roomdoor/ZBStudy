package codiingTest.codingTest16.p4;


import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int[][] isVisited;

	public static int solution(int[][] heights) {
		int xlen = heights.length;
		int ylen = heights[0].length;
		isVisited = new int[xlen][ylen];
		for (int i = 0; i < xlen; i++) {
			Arrays.fill(isVisited[i], Integer.MAX_VALUE);
		}
		isVisited[0][0] = -1;

		PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[2] - y[2]);

		queue.add(new int[]{0, 0, 0});// x,y, 최소의 최대값

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			int x = cur[0];
			int y = cur[1];
			int minMax = cur[2];
			if (x == xlen - 1 && y == ylen - 1) {
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if (isPossible(nextX, nextY)) {
					int nMM = Math.max(Math.abs(heights[nextX][nextY] - heights[x][y]), minMax);
					if (isVisited[nextX][nextY] > nMM) {
						queue.add(new int[]{nextX, nextY, nMM});
						isVisited[nextX][nextY] = nMM;
					}
				}
			}
		}

		return isVisited[xlen - 1][ylen - 1];
	}

	public static boolean isPossible(int x, int y) {
		return x >= 0 && y >= 0 && x < isVisited.length && y < isVisited[0].length;
	}

	public static void main(String[] args) {
		int[][] a = new int[][]{{1, 2, 2}, {3, 10, 2}, {5, 3, 5}};
		System.out.println(solution(a));
	}
}