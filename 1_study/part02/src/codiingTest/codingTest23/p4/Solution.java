package codiingTest.codingTest23.p4;


import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	public static int[][] imageStatic;
	public static boolean[][] isVisited;
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int left;
	public static int right;
	public static int top;
	public static int bottom;

	public static int solution(int[][] image, int x, int y) {
		long start1 = System.nanoTime();
		long start2 = System.nanoTime();
		imageStatic = image;
		int lenX = image.length;
		int lenY = image[0].length;
		isVisited = new boolean[lenX][lenY];
		int answer = 0;
		left = lenY;
		right = 0;
		top = lenX;
		bottom = 0;
		long end2 = System.nanoTime();

		long start3 = System.nanoTime();
		loof:
		for (int i = 0; i < lenX; i++) {
			for (int j = 0; j < lenY; j++) {
				if (image[i][j] == 1) {
					bfs(i, j);
					break loof;
				}
			}
		}
		long end3 = System.nanoTime();

		long start4 = System.nanoTime();
		answer = curArea(x, y);
		long end4 = System.nanoTime();
		long end1 = System.nanoTime();
		System.out.println("1 : " + (end1 - start1));
		System.out.println("2 : " + (end2 - start2));
		System.out.println("3 : " + (end3 - start3));
		System.out.println("4 : " + (end4 - start4));
		return answer;
	}

	public static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[]{x, y});

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = cur[0] + dx[i];
				int nextY = cur[1] + dy[i];
				if (isPossible(nextX, nextY)) {
					queue.add(new int[]{nextX, nextY});
					isVisited[nextX][nextY] = true;
					update(nextX, nextY);
				}
			}
		}
	}

	public static boolean isPossible(int x, int y) {
		return x >= 0 && y >= 0 && x < imageStatic.length && y < imageStatic[0].length
			&& imageStatic[x][y] == 1 && !isVisited[x][y];
	}

	public static void update(int x, int y) {
		left = Math.min(left, y);
		right = Math.max(right, y);
		top = Math.min(top, x);
		bottom = Math.max(bottom, x);
	}

	public static int curArea(int x, int y) {
		int answer = 0;
		if (x >= top && x <= bottom && y >= left && y <= right) {
			answer = (right - left + 1) * (bottom - top + 1);
		} else {
			update(x, y);
			answer = (right - left + 1) * (bottom - top + 1);
		}

		return answer;
	}

	public static void main(String[] args) {
		int[][] a = new int[][]{{0, 0, 1, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 1, 1, 0}};
		System.out.println(solution(a, 0, 2));
	}

}