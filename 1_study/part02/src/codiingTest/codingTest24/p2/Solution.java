package codiingTest.codingTest24.p2;


public class Solution {

	public static int solution(String[] words) {
		int answer = 0;

		int len = words.length;
		String frist;
		String second;
		StringBuilder sb;
		for (int i = 0; i < len; i++) {
			frist = words[i];
			for (int j = 0; j < len; j++) {
				sb = new StringBuilder();
				if (i != j) {
					second = words[j];
					sb.append(frist).append(second);
					if (sb.toString().equals(sb.reverse().toString())) {
						answer++;
					}

				}
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		String[] s = new String[]{"zero", "base", "esab", "esabe", "orez"};
		System.out.println(solution(s));

	}
}