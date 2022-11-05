package codiingTest.codingTest24.p5;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

	public static int[] solution(int[] nums) {
		int[] answer = {};
		Arrays.sort(nums);
		List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			List<Integer> mapList = map.getOrDefault(list.get(i), new ArrayList<>());
			mapList.add(i);
			map.put(list.get(i), mapList);
		}
		List<Integer> result = new ArrayList<>();
		for (Integer num : list) {
			if (map.containsKey(num * 2) && map.get(num).size() != 0) {
				if (map.get(num * 2).size() != 0) {
					result.add(num);
					map.get(num * 2).remove(0);
					map.get(num).remove(0);
				} else {
					return answer;
				}
			}
		}

		answer = result.stream().mapToInt(Integer::intValue).toArray();

		return answer;
	}

	public static void main(String[] args) {
		int[] a = new int[]{1, 8, 2, 4, 5, 10};

//		System.out.println(Arrays.toString(solution(a)));

		a = new int[]{20, 16, 8, 8, 18, 9, 4, 8, 6, 12, 10};

		System.out.println(Arrays.toString(solution(a)));
	}

}