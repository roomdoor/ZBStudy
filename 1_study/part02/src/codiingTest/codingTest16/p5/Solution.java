package codiingTest.codingTest16.p5;


import java.util.PriorityQueue;

public class Solution {
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static boolean[][] isVisited;

	public static int solution(int[][] heights) {
		int answer = 0;
		int xlen = heights.length;
		int ylen = heights[0].length;
		isVisited = new boolean[xlen][ylen];

		PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[2] - y[2]);    //{x, y, 높이}

		for (int i = 0; i < xlen; i++) {
			queue.add(new int[]{i, 0, heights[i][0]});
			queue.add(new int[]{i, ylen - 1, heights[i][ylen - 1]});
			isVisited[i][0] = true;
			isVisited[i][ylen - 1] = true;
		}

		for (int i = 0; i < ylen; i++) {
			queue.add(new int[]{0, i, heights[0][i]});
			queue.add(new int[]{xlen - 1, i, heights[xlen - 1][i]});
			isVisited[0][i] = true;
			isVisited[xlen - 1][i] = true;
		}

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			int height = cur[2];

			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];

				if (isPossible(nextX, nextY) && heights[x][y] > heights[nextX][nextY]) {
					isVisited[nextX][nextY] = true;
					answer += heights[x][y] - heights[nextX][nextY];
					heights[nextX][nextY] = heights[x][y];
					queue.add(new int[]{nextX, nextY, heights[nextX][nextY]});
				}
			}
		}

		return answer;
	}

	public static boolean isPossible(int x, int y) {
		return x >= 0 && y >= 0 && x < isVisited.length && y < isVisited[0].length
			&& !isVisited[x][y];
	}

	public static void main(String[] args) {
		int[][] heights = {
			{3, 4, 5, 4, 3, 3},
			{3, 2, 1, 1, 2, 3},
			{4, 2, 1, 1, 2, 3},
			{3, 3, 3, 3, 5, 3}};

		System.out.println(solution(heights));
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


