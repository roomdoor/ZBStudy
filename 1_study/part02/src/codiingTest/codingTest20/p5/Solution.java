package codiingTest.codingTest20.p5;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

	public static int[] union;

	public static int solution(String s, String t, String[][] similarWords) {
		List<String> words = Arrays.stream(similarWords).flatMap(Arrays::stream)
			.collect(Collectors.toSet()).stream()
			.toList();

		Map<String, Integer> similarMap = new HashMap<>();

		for (int i = 0; i < words.size(); i++) {
			similarMap.put(words.get(i), i);
		}

		union = new int[words.size()];
		for (int i = 0; i < union.length; i++) {
			union[i] = i;
		}

		for (int i = 0; i < similarWords.length; i++) {

			int a = find(similarMap.get(similarWords[i][0]));
			int b = find(similarMap.get(similarWords[i][1]));

			if (a > b) {
				union[a] = b;
			} else {
				union[b] = a;
			}
		}

		String[] sArr = s.split(" ");
		String[] tArr = t.split(" ");

		int answer = 0;
		for (int i = 0; i < sArr.length; i++) {
			if (similarMap.containsKey(sArr[i]) && similarMap.containsKey(tArr[i]) &&
				find(similarMap.get(sArr[i])) == find(similarMap.get(tArr[i]))) {
				answer++;
			} else if (sArr[i].equals(tArr[i])) {
				answer++;
			}
		}

		return answer;
	}

	public static int find(int x) {
		if (union[x] == x) {
			return x;
		} else {
			return find(union[x]);
		}
	}


	public static void main(String[] args) {
		String s = "you are reading this";
		String t = "i am writing that";
		String[][] ss = new String[][]{{"you", "he"}, {"he", "i"}, {"are", "is"}, {"is", "has"},
			{"has", "am"}, {"reading", "watching"}, {"this", "that"}, {"that", "those"}};
		System.out.println(solution(s, t, ss));

		s = "algorithm study should be done at once";
		t = "datastructure lecture must be done this easy";
		ss = new String[][]{{"algorithm", "lecture"}, {"datastructure", "done"}, {"should", "must"},
			{"study", "datastructure"}};
		System.out.println(solution(s, t, ss));

		s = "what if there are two similar sentences";
		t = "what if there are two similar sentences";
		ss = new String[][]{{"one", "two"}};
		System.out.println(solution(s, t, ss));


		s = "what if there are two similar sentences";
		t = "sentences similar two are there if what";
		ss = new String[][]{{"what", "sentences"}, {"if", "similar"}, {"there", "two"}};
		System.out.println(solution(s, t, ss));


		s =   "this is fun";
		t = "i am god";
		ss = new String[][]{{"this", "fun"}, {"is", "is"}, {"am", "am"}, {"god", "am"}, {"god", "i"}};
		System.out.println(solution(s, t, ss));
	}
}
// 3, 3, 7, 7, 0