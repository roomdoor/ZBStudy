package codiingTest.codingTest21.p2;


public class Solution {

	public static boolean solution(String s, String t) {
		boolean answer = true;
		if (s.equals(t)) {
			return false;
		}
		int left = 0;
		int right = s.length();


		if (s.length() == t.length()) {
			while (left < right) {
				int mid = (left + right) / 2;
				boolean isRight = !s.substring(mid).equals(t.substring(mid));
				boolean isLeft = !s.substring(0, mid).equals(t.substring(0, mid));

				if (isLeft && isRight) {
					return false;
				} else if (isLeft) {
					right = mid;
				} else if (isRight) {
					left = mid + 1;
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		String s1 = "zEroBase";
		String s2 = "zeroBase";
		System.out.println(solution(s1, s2));
	}
}