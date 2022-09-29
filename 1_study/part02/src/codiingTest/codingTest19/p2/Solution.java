package codiingTest.codingTest19.p2;


public class Solution {
	public boolean solution(String param0) {
		boolean isNumber = false;
		boolean isUpperCase = false;
		if (param0.length() < 5) {
			return false;
		}

		for (char c : param0.toCharArray()) {
			if (c >= '0' && c <= '9') {
				isNumber = true;
			}

			if (c >= 'A' && c <= 'Z') {
				isUpperCase = true;
			}
		}
		return isNumber && isUpperCase;
	}
}