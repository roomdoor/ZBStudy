package codiingTest.codingTest22.p2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static int[][] solution(int[][] s, int[][] t) {
		int[] sDecode = decoder(s);
		int[] tDecode = decoder(t);
		int[] uEncode = new int[sDecode.length];
		System.out.println(Arrays.toString(sDecode));
		System.out.println(Arrays.toString(tDecode));

		for (int i = 0; i < sDecode.length; i++) {
			uEncode[i] = sDecode[i] * tDecode[i];
		}
		System.out.println(Arrays.toString(uEncode));
		return encoder(uEncode);
	}

	public static int[] decoder(int[][] a) {
		List<Integer> result = new ArrayList<>();

		for (int[] ints : a) {
			int num = ints[0];
			int count = ints[1];

			for (int j = 0; j < count; j++) {
				result.add(num);
			}
		}
		return result.stream().mapToInt(Integer::intValue).toArray();
	}

	public static int[][] encoder(int[] a) {
		List<List<Integer>> result = new ArrayList<>();
		int num = a[0];
		int count = 1;
		for (int i = 1; i < a.length; i++) {
			if (num == a[i]) {
				count++;
				if (i == a.length - 1) {
					result.add(List.of(num, count));
				}
			} else {
				result.add(List.of(num, count));
				num = a[i];
				count = 1;
			}
		}

		int[][] answer = new int[result.size()][2];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i).stream().mapToInt(Integer::intValue).toArray();
		}
		return answer;
	}

	public static void main(String[] args) {
		int[][] s = new int[][]{{1, 4}, {2, 2}};
		int[][] t = new int[][]{{6, 4}, {3, 2}};
		System.out.println(Arrays.deepToString(solution(s, t)));

		s = new int[][]{{1, 3}, {1, 4}};
		t = new int[][]{{5, 2}, {3, 2}, {2, 3}};
		System.out.println(Arrays.deepToString(solution(s, t)));
	}
}