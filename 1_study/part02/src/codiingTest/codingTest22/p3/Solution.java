package codiingTest.codingTest22.p3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static List<LowAndCulAndValue> ss;
	public static List<LowAndCulAndValue> tt;

	public static class LowAndCulAndValue {

		int low;
		int cul;
		int value;

		public LowAndCulAndValue(int low, int cul, int value) {
			this.low = low;
			this.cul = cul;
			this.value = value;
		}
	}

	public static int[][] solution(int[][] s, int[][] t) {
		pring(s);
		pring(t);
		int[][] a = new int[s.length][t[0].length];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < t[0].length; j++) {
				for (int k = 0; k < s[0].length; k++) {
					if (s[i][k] != 0 && t[k][j] != 0) {
						int aa = s[i][k] * t[k][j];
						a[i][j] += aa;
					}
				}
			}
		}
		pring(a);
		return a;

//		ss = extracted(s);
//		tt = extracted(t);
//
//		int m = s.length;
//		int l = t[0].length;
//		int[][] answer = new int[m][l];
//
//		for (int i = 0; i < m; i++) {
//			for (int j = 0; j < l; j++) {
//				answer[i][j] = cul(i, j);
//			}
//		}
//
//		pring(answer);
//		return answer;
	}

	private static List<LowAndCulAndValue> extracted(int[][] s) {
		List<LowAndCulAndValue> aa = new ArrayList<>();
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				if (s[i][j] != 0) {
					aa.add(new LowAndCulAndValue(i, j, s[i][j]));
				}
			}
		}

		return aa;
	}

	public static int cul(int m, int l) {
		int answer = 0;
		for (LowAndCulAndValue s : ss) {
			if (s.low == m) {
				for (LowAndCulAndValue t : tt) {
					if (t.cul == l && t.low == s.cul) {
						int a = s.value * t.value;
						answer += a;
					}
				}
			}
		}

		return answer;
	}

	public static void pring (int[][] a) {
		StringBuilder sb = new StringBuilder();
		for (int[] ints : a) {
			for (int j = 0; j < a[0].length; j++) {
				sb.append(ints[j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		int[][] s = new int[][]{{1, 0, 0}, {0, 0, 3}};
		int[][] t = new int[][]{{5, 0, 2}, {0, 0, 0}, {0, 0, -1}};



		System.out.println(Arrays.deepToString(solution(s, t)));


	}
}