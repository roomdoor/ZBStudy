package codiingTest.codingTest24.p10;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static int[][] solution(int[][] matrix) {
		Map<Integer, Integer> map;
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] answer = new int[row][col];

		for (int i = 0; i < row; i++) {
			map = new HashMap<>();
			for (int j = 0; j < col; j++) {
				map.put(matrix[i][j], j);
			}
			int[] line = matrix[i].clone();
			Arrays.sort(line);
			for (int j = 0; j < col; j++) {
				answer[i][map.get(line[j])] = j + 1;
			}
		}

		for (int i = 0; i < col; i++) {
			map = new HashMap<>();
			int[] column = new int[col];
			for (int j = 0; j < row; j++) {
				map.put(matrix[j][i], j);
				column[j] = matrix[j][i];
			}
			Arrays.sort(column);

		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}

		return answer;
	}

	public static void main(String[] args) {
		int[][] a = new int[][]{{169, 117, 142, 88, 10}, {186, 150, 101, 63, 127},
			{228, 83, 12, 8, 209}, {75, 11, 123, 244, 213}, {154, 189, 34, 174, 99}};

		System.out.println(Arrays.deepToString(solution(a)));
	}

}