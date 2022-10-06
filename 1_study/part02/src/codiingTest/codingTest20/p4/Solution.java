package codiingTest.codingTest20.p4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

	public static List<Set<String>> nDP = new ArrayList<>();

	public static String[] solution(int n) {
		nDP.add(new HashSet<>());
		Set<String> n1 = new HashSet<>();
		n1.add("0");
		n1.add("1");
		n1.add("8");
		nDP.add(n1);
		n1 = new HashSet<>();
		n1.add("11");
		n1.add("69");
		n1.add("88");
		n1.add("96");
		nDP.add(n1);

		for (int i = 0; i < 13; i++) {
			nDP.add(new HashSet<>());
		}

		if (n == 1 || n == 2) {
			List<String> sser = new ArrayList<>(nDP.get(n));
			Collections.sort(sser);
			return sser.toArray(String[]::new);
		}
		List<String> dfs = new ArrayList<>(dfs(n));
		Collections.sort(dfs);

		return dfs.toArray(String[]::new);
	}

	public static List<String> dfs(int n) {
		if (n == 1 || n == 2) {
			return new ArrayList<>(nDP.get(n));
		}

		if (n % 2 == 0) {
			Set<String> strings = nDP.get(n);
			List<String> halfList = nDP.get(n / 2).stream().toList();
			for (String item : halfList) {
				strings.add(item + item);
			}
			List<String> listMinus2 = nDP.get(n - 2).stream().toList();
			List<String> list2 = nDP.get(2).stream().toList();
			for (String value : list2) {
				for (String s : listMinus2) {
					strings.add(sumString(s, value));
					strings.add(sumString(value, s));
				}
			}

		} else {
			Set<String> strings = nDP.get(n);
			List<String> listMinus2 = nDP.get(n - 2).stream().toList();
			List<String> list2 = nDP.get(2).stream().toList();
			for (String value : list2) {
				for (String s : listMinus2) {
					String add1 = sumString(s, value);
					strings.add(add1);

					String add2 = sumString(value, s);
					if (!add2.startsWith("0")) {
						strings.add(add2);
					}
				}
			}
		}

		return nDP.get(n).stream().toList();
	}

	public static String sumString(String a, String b) {
		String substring1 = a.substring(a.length() / 2);
		String substring2 = a.substring(0, a.length() / 2);

		return substring1 + b + substring2;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(1)));
		System.out.println(Arrays.toString(solution(2)));
		System.out.println(Arrays.toString(solution(3)));
		System.out.println(Arrays.toString(solution(4)));
	}
}

// n = 1 ->	{"0", "1", "8"},

// n = 2 ->	{"11", "69", "88", "96"},

// n = 3 -> n1 * n2   {"101", "111", "181",
// 						"609", "619", "689",
// 						"808", "818", "888",
// 						"906", "916", "986"}

// n = 4 -> n2 * n2   {"1111", "1691", "1881", "1961",
// 						"6119", "6699", "6889", "6969",
// 						"8118", "8698", "8888", "8968",
// 						"9116", "9696", "9886", "9966",
// 						}

// n = 5 -> n3 * n2

// n = 6 -> n4 * n2 + n3 * n3

// n = 7 -> n6 * n1 + n5 * n2 + n4 * n3






