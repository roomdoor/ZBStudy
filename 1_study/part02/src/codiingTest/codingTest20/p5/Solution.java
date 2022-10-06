package codiingTest.codingTest20.p5;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

	public static Map<String, Set<String>> similarSet;

	public static int solution(String s, String t, String[][] similarWords) {
		int answer = 0;
		similarSet = new HashMap<>();

		for (String[] similarWord : similarWords) {
			if (!similarSet.containsKey(similarWord[0])) {
				Set<String> list = new HashSet<>();
				list.add(similarWord[1]);
				similarSet.put(similarWord[0], list);
			} else {
				Set<String> list = similarSet.get(similarWord[0]);
				list.add(similarWord[1]);
				similarSet.put(similarWord[1], list);
			}

			if (!similarSet.containsKey(similarWord[1])) {
				Set<String> list = new HashSet<>();
				list.add(similarWord[0]);
				similarSet.put(similarWord[1], list);
			} else {
				Set<String> list = similarSet.get(similarWord[1]);
				list.add(similarWord[0]);
				similarSet.put(similarWord[0], list);
			}
		}

		String[] sWords = s.split(" ");
		String[] tWords = t.split(" ");

		for (int i = 0; i < sWords.length; i++) {
			if (isSimilar(sWords[i], tWords[i], false, "")) {
				answer++;
			}
		}
		return answer;
	}

	public static boolean isSimilar(String sWord, String tWord, boolean changed, String before) {
		if (similarSet.containsKey(sWord)) {
			if (similarSet.get(sWord).contains(tWord)) {
				return true;
			} else {
				for (String sWordValue : similarSet.get(sWord)) {
					if (similarSet.containsKey(sWordValue) && !sWordValue.equals(before)) {
						return isSimilar(sWordValue, tWord, false, sWord);
					}
				}
			}

		} else if (similarSet.containsKey(tWord) && !changed) {
			isSimilar(tWord, sWord, true, "");
		}
		return false;
	}

	public static void main(String[] args) {
		String s = "zerobase is awesome";
		String t = "courses are great";
		String[][] ss = new String[][]{
			{"zerobase", "courses"},
			{"is", "am"},
			{"are", "am"},
			{"awesome", "fine"},
			{"fine", "great"}};
//		System.out.println(solution(s, t, ss));

		s = "zerobase is awesome";
		t = "games are fine";
		ss = new String[][]{
			{"zerobase", "courses"},
			{"is", "am"},
			{"are", "am"},
			{"awesome", "fine"},
			{"fine", "great"}};
		System.out.println(solution(s, t, ss));

		s = "1 2";
		t = "9 10";
		ss = new String[][]{{"1", "3"}, {"4", "3"}, {"4", "9"}};
		System.out.println(solution(s, t, ss));
	}
}
// 13 31
// 13 43 34
// 13 49 34 94