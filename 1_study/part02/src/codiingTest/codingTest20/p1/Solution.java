package codiingTest.codingTest20.p1;


import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

	public static boolean solution(String s, String t) {
		String[] sWord = s.split(" ");
		String[] tWord = t.split(" ");

		for (int i = 0; i < sWord.length; i++) {
			if (sWord[i].length() != tWord[i].length()) {
				return false;
			}
			Set<String> sWordSet = Arrays.stream(
				sWord[i].substring(1, sWord[i].length() - 1).split("")).collect(Collectors.toSet());
			Set<String> tWordSet = Arrays.stream(
				tWord[i].substring(1, tWord[i].length() - 1).split("")).collect(Collectors.toSet());

			sWordSet.removeAll(tWordSet);

			if (!sWordSet.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String s = "캠브릿지 대학의 연구결과에 따르면";
		String t = "캠릿브지 대학의 연결과구에 따르면";
//		System.out.println(solution(s, t));

		s = "제로베이스는 명실상부 최고의 온라인 교육플랫폼";
		t = "제베로스이는 명상실부 최고의 온라인 교육랫폼플";
		System.out.println(solution(s, t));
	}
}