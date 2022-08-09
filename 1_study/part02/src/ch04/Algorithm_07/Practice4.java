package ch04.Algorithm_07;// Practice
// 주어진 nums 배열에서 3 개의 합이 0이 되는 모든 숫자들을 출력하세요.
// 중복된 숫자 셋은 제외하고 출력하세요.

// 입출력 예시
// nums: {-1, 0, 1, 2, -1, -4};
// 출력: [[-1, -1, 2], [-1, 0, 1]]

// nums: {1, -7, 6, 3, 5, 2}
// 출력: [[-7, 1, 6], [-7, 2, 5]]


import java.util.*;
import java.util.stream.Collectors;

public class Practice4 {
    public static ArrayList<ArrayList<Integer>> solution(int[] nums) {
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int p1 = i + 1;
            int p2 = nums.length - 1;
            while (p1 < p2) {
                if (i == p1) {
                    p1++;
                }

                if (i == p2) {
                    p2--;
                }

                int sum = nums[i] + nums[p1] + nums[p2];
                if (sum == 0) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[p1]);
                    temp.add(nums[p2]);
                    Collections.sort(temp);
                    result.add(temp);
                    p1 += 1;
                } else if (sum < 0) {
                    p1++;
                } else {
                    p2--;
                }
            }

        }

        ArrayList<ArrayList<Integer>> r = new ArrayList<>(result);

        return result;
    }

    public static void main(String[] args) {
        // Test code:wq
        int[] nums = {-1, 0, 1, 2, -1, -4};     // [[-1, -1, 2], [-1, 0, 1]]
        System.out.println(solution(nums));

        nums = new int[]{1, -7, 6, 3, 5, 2};   // [[-7, 1, 6], [-7, 2, 5]]
        System.out.println(solution(nums));
    }
}
