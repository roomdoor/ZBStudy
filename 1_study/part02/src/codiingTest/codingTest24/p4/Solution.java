package codiingTest.codingTest24.p4;


public class Solution {

	public static int[][] board;

	public static int solution(int[][] board) {
		Solution.board = board;
		int answer = 0;
		int x = 0;
		int y = 0;
		int direction = 0;
		int count = 0;
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		board[0][0] = 2;
		while (true) {
			for (int i = direction; true; i++) {
				i %= 4;
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if (isPossible(nextX, nextY)) {
					x = nextX;
					y = nextY;
					direction = i;
					if (board[x][y] == 2) {
						count++;
					} else {
						count = 0;
					}
					board[x][y] = 2;
					break;
				}
			}

			if ((x == 0 && y == 0) || count >= 101) {
				for (int[] row : board) {
					for (int col : row) {
						if (col == 2) {
							answer++;
						}
					}
				}
				break;
			}
		}

		return answer;
	}

	public static boolean isPossible(int x, int y) {
		return x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] != 1;
	}

	public static void main(String[] args) {
		int[][] a = new int[][]{
			{0, 0, 0, 0},
			{0, 1, 1, 0},
			{0, 0, 0, 0},
			{1, 0, 1, 1}};
		System.out.println(solution(a));

	}
}