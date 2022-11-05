package codiingTest.codingTest24.p9;


import java.util.Arrays;

public class Solution {

	public static int[] parents;

	public static int solution(int n, int[] cost, int[][] line) {
		int answer = 0;
		parents = new int[n];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		int[][] houseToHouse = new int[n + 1][n + 1];
		boolean[] linkedHouse = new boolean[n + 1];
		for (int[] l : line) {
			houseToHouse[l[0]][l[1]] = l[2];
			houseToHouse[l[1]][l[0]] = l[2];
			linkedHouse[l[0]] = true;
			linkedHouse[l[1]] = true;
		}

		for (int i = 0; i < n + 1; i++) {
			System.out.println(Arrays.toString(houseToHouse[i]));
		}

		for (int i = 0; i < linkedHouse.length; i++) {
			if (!linkedHouse[i]) {
				answer += cost[i];
			}
		}

		return answer;
	}

	public int find(int x) {
		if (parents[x] == x) {
			return x;
		} else {
			return find(parents[parents[x]]);
		}
	}

	public void union(int x, int y) {
		x = find(x);
		y = find(y);

//		if ()
	}

	public static void main(String[] args) {
		int n = 5;
		int[] a = new int[]{931, 989, 553, 947, 368};
		int[][] b = new int[][]{{1, 2, 91}, {2, 1, 114}, {3, 4, 214}, {3, 0, 262}, {5, 1, 337},
			{4, 5, 368}, {3, 5, 376}, {0, 4, 479}, {1, 3, 498}, {2, 5, 553}, {4, 1, 617},
			{2, 1, 653}, {3, 0, 680}, {1, 4, 806}, {0, 5, 931}, {3, 5, 947}, {1, 5, 989}};

		System.out.println(solution(n, a, b));
	}
}

