package codiingTest.codingTest21.p4;


import java.util.Arrays;

public class Solution {

	public static int[][] board;
	public static boolean[][] isVisited;
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};


	public static int[][] solution(int[][] board) {
		Solution.board = board;
		isVisited = new boolean[board.length][board[0].length];

		boolean isRemoved = true;

		while (isRemoved) {
			isRemoved = removeScan();
			print();
			if (isRemoved) {
				blockDownScan();
			}
		}

		return board;
	}

	public static boolean removeScan() {
		boolean isRemoved = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != 0) {
					isVisited = new boolean[board.length][board[0].length];
					int count = countBlock(i, j, board[i][j]);
					if (count >= 3) {
						isRemoved = true;
						System.out.println("3개 이상의 블럭 숫자 " + board[i][j] + "가 붙어 있다.");
						System.out.println(i + " " + j);
						System.out.println(count);
						removeBlock(i, j, board[i][j]);
					}
				}
			}
		}

		return isRemoved;
	}

	public static void removeBlock(int x, int y, int blockNum) {
		board[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (isPossible(nextX, nextY) && board[nextX][nextY] == blockNum) {
				removeBlock(nextX, nextY, blockNum);
			}
		}
	}

	public static int countBlock(int x, int y, int blockNum) {
		isVisited[x][y] = true;
		int count = 1;
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (isPossible(nextX, nextY) && !isVisited[nextX][nextY]
				&& board[nextX][nextY] == blockNum) {
				count += countBlock(nextX, nextY, blockNum);
			}
		}
		return count;
	}

	public static void blockDownScan() {
		for (int i = board.length - 2; i >= 0; i--) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != 0 && board[i + 1][j] == 0) {
					blockDown(i, j, i + 1);
				}
			}
		}

	}

	public static void blockDown(int x, int y, int nextX) {
		if (nextX < board.length && board[x][y] != 0 && board[nextX][y] == 0) {
			blockDown(x, y, nextX + 1);
		} else {
			board[nextX - 1][y] = board[x][y];
			board[x][y] = 0;
			print();
		}
	}

	public static boolean isPossible(int x, int y) {
		return x >= 0 && y >= 0 && x < board.length && y < board[0].length;
	}

	public static void print() {
		for (int[] ints : board) {
			System.out.println(Arrays.toString(ints));
		}
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args) {
		int[][] a = new int[][]{
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 1, 2, 3, 0},
			{0, 1, 2, 3, 4, 4},
			{1, 2, 3, 4, 4, 4}};

		int[][] solution = solution(a);

		for (int i = 0; i < solution.length; i++) {
			System.out.println(Arrays.toString(solution[i]));
		}

	}
}