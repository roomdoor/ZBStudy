package codiingTest.codingTest15.p5;

public class Solution {

    public static int solution(int[] nums) {
        int answer = 0;
        int n3 = nums.length;
        int n = n3 / 3;
        int n2 = 2 * n;
        int fistSum = 0;
        int secondSum = 0;

        for (int i = n; i < n2; i++) {
            fistSum += nums[i];
        }

        for (int i = n2; i < n3; i++) {
            secondSum += nums[i];
        }

        answer = fistSum - secondSum;

        for (int i = 0; i < n; i++) {
            fistSum += nums[i];
            fistSum -= nums[n + i];
            answer = Math.min(answer, fistSum - secondSum);
        }


        for (int i = n; i < n2; i++) {
            secondSum += nums[i];
            secondSum -= nums[n2 + i - n];
            answer = Math.min(answer, fistSum - secondSum);
        }


        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{7, 9, 5, 8, 1, 3};
        System.out.println(solution(a));
    }
}


