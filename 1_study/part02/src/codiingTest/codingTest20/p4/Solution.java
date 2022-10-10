package codiingTest.codingTest20.p4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Solution {

	protected static List<Integer> halfNum;
	protected static List<String> answer;
	protected static int N;
	protected static int H;
	protected static Map<Integer, Integer> map = Map.of(
		0, 0,
		1, 1,
		8, 8,
		6, 9,
		9, 6);

	public static String[] solution(int n) {
		N = n;
		H = (n + 1) / 2;

		halfNum = new ArrayList<>();
		answer = new ArrayList<>();

		if (n == 1) {
			for (Integer a : map.keySet()) {
				if (a.equals(map.get(a))) {
					halfNum.add(a);
				}
			}
			return halfNum.stream().sorted().map(Objects::toString).toArray(String[]::new);
		}

		makeHalfNum();

		return answer.stream().sorted().toArray(String[]::new);
	}

	public static void makeHalfNum() {
		if (halfNum.size() == H) {
			addAnswer();
			return;
		}

		for (Integer a : map.keySet()) {
			halfNum.add(a);

			if (isPossible()) {
				makeHalfNum();
			}

			halfNum.remove(halfNum.size() - 1);
		}
	}

	private static boolean isPossible() {
		if (halfNum.get(0) == 0) {
			return false;
		}

		return N % 2 != 1 ||
			halfNum.size() != H ||
			halfNum.get(halfNum.size() - 1).equals(map.get(halfNum.get(halfNum.size() - 1)));
	}

	public static void addAnswer() {
		List<Integer> reverseHalfNum = new ArrayList<>(halfNum);
		Collections.reverse(reverseHalfNum);

		if (N % 2 == 1) {
			reverseHalfNum.remove(0);
		}
		StringBuilder result = new StringBuilder();
		for (Integer a : halfNum) {
			result.append(a);
		}
		for (Integer a : reverseHalfNum) {
			result.append(a);
		}

		answer.add(result.toString());
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(1)));
		System.out.println(Arrays.toString(solution(2)));
		System.out.println(Arrays.toString(solution(3)));
		System.out.println(Arrays.toString(solution(4)));
	}
}