package codiingTest.codingTest18.p3;


public class Solution {

	public String solution(int[][] points) {
		String answer = "";
		answer = threePointPositioning(points);
		return answer;
	}

	public static String threePointPositioning(int[][] points) {
		double[][] po = new double[3][2];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				po[i][j] = points[i][j];
			}
		}

		double m1 = (po[0][1] - po[1][1]) / (po[0][0] - po[1][0]);
		double m2 = (po[1][1] - po[2][1]) / (po[1][0] - po[2][0]);

		// y = m1 * x + b
		// b = y - m1 * x

		if (m1 == m2) {
			return "LINE";
		}

		double b = po[0][1] - m1 * po[0][0];

		// graph right
		double v = po[2][1] - m1 * po[2][0] - b;

		if (v > 0) {
			if (m1 > 0) {
				if (po[0][0] < po[1][0]) {
					return "CCW";
				} else {
					return "CW";
				}
			} else {
				if (po[0][0] > po[1][0]) {
					return "CCW";
				} else {
					return "CW";
				}
			}
		}

		// graph left
		else {
			if (m1 < 0) {
				if (po[0][0] > po[1][0]) {
					return "CCW";
				} else {
					return "CW";
				}
			} else {
				if (po[0][0] < po[1][0]) {
					return "CCW";
				} else {
					return "CW";
				}
			}
		}
	}


	public static void main(String[] args) {
		int[][] a = new int[][]{{0, 0}, {2, 2}, {6, 4}};
		System.out.println(threePointPositioning(a));
	}
}