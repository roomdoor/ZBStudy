package codiingTest.codingTest16.p1;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static int[] solution(int[] nums, int k) {

		Map<Integer, Integer> numCount = new HashMap<>();

		for (Integer num : nums) {
			numCount.put(num, numCount.getOrDefault(num, 0) + 1);
		}

		int len = nums.length;
		int[][] arr = new int[len][2];
		int i = 0;

		for (int key : numCount.keySet()) {
			arr[i][0] = key;
			arr[i][1] = numCount.get(key);
			i++;
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] - o2[1] == 0) {
					return o1[0] - o2[0];
				} else {
					return o2[1] - o1[1];
				}
			}
		});

		int[] answer = new int[k];
		for (int j = 0; j < k; j++) {
			answer[j] = arr[j][0];
		}

		return answer;
	}
}