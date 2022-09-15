package codiingTest.codingTest17.p3;


public class Solution {

	public static int[][] solution(int M, int N, int[][] shapes, int[] colors) {
		int[][] answer = new int[M][N];

		int len = shapes.length;
		for (int i = len - 1; i >= 0; i--) {
			int centerX = shapes[i][1];
			int centerY = shapes[i][0];
			int radius = shapes[i][2];
			int[] range = range(centerX, centerY, radius, M, N);
			double r = Math.pow(radius, 2);

			for (int j = range[0]; j < range[1]; j++) {
				for (int k = range[2]; k < range[3]; k++) {
					if (answer[j][k] == 0) {
						double dis = curDis(j, k, centerX, centerY);
						if (dis <= r) {
							answer[j][k] = colors[i];
						}
					}
				}
			}
		}

		for (int i = 0; i <= N; i++) {
			System.out.printf("%5d", i - 1);
		}
		System.out.println();
		for (int i = 0; i < M; i++) {
			System.out.printf("%5d", i);
			for (int j = 0; j < N; j++) {
				System.out.printf("%5d", answer[i][j]);
			}
			System.out.println();
		}

		return answer;
	}

	public static int[] range(int centerX, int centerY, int radius, int M, int N) {
		int[] result = new int[]{0, M, 0, N}; // x start, x end, y start, y end

		if (centerX - radius >= 0) {
			result[0] = centerX - radius;
		}

		if (centerX + radius < M) {
			result[1] = centerX + radius + 1;
		}

		if (centerY - radius >= 0) {
			result[2] = centerY - radius;
		}

		if (centerY + radius < N) {
			result[3] = centerY + radius + 1;
		}

		return result;
	}

	public static double curDis(int x, int y, int centerX, int centerY) {
		// 좌 상단
		if (x < centerX && y < centerY) {
			return Math.pow(x + 0.5 - centerX, 2) + Math.pow(y + 0.5 - centerY, 2);
		}

		// 우 상단
		if (x > centerX && y < centerY) {
			return Math.pow(x - 0.5 - centerX, 2) + Math.pow(y + 0.5 - centerY, 2);
		}

		// 좌 하단
		if (x < centerX && y > centerY) {
			return Math.pow(x + 0.5 - centerX, 2) + Math.pow(y - 0.5 - centerY, 2);
		}

		// 우 하단
		if (x > centerX && y > centerY) {
			return Math.pow(x - 0.5 - centerX, 2) + Math.pow(y - 0.5 - centerY, 2);
		}

		if (x == centerX && y == centerY) {
			return 0;
		}

		return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
	}

	public static void main(String[] args) {
		int a = 10;
		int b = 15;
		int[][] aa = new int[][]{{5, 4, 3}, {8, 5, 4}};
		int[] bb = new int[]{50, 200};

		a = 6;
		b = 6;
		aa = new int[][]{{3, 3, 2}};
		bb = new int[]{128};

		a = 20;
		b = 20;
		aa = new int[][]{{3, 3, 5}, {14, 3, 9}, {19, 20, 5}};
		bb = new int[]{100, 210, 70};
		solution(a, b, aa, bb);

	}
}