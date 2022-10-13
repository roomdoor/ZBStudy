package codiingTest.codingTest21.p1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {


	public static String[] solution(String[] phrases) {
		Set<String> answer = new HashSet<>();
		int len = phrases.length;
		for (String s : phrases) {
			for (String phrase : phrases) {
				String[] sWords = phrase.split(" ");
				if (s.startsWith(sWords[sWords.length - 1])) {
					answer.add(phrase + s.substring(sWords[sWords.length - 1].length()));
				}

				if (s.endsWith(sWords[0])) {
					answer.add(s + phrase.substring(sWords[0].length()));
				}
			}
		}

		return answer.stream().sorted().toArray(String[]::new);
	}
}