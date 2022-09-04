package codiingTest.codingTest15.p2;


import java.util.Arrays;

public class Solution {
    public static int solution(int[] buckets, int m) {
        int answer = 0;
        int len = buckets.length;
        Arrays.sort(buckets);

        int left = 0;
        int right = buckets[len - 1];

        while (left <= right) {
            int dis = left + (right - left) / 2;
            int ballNum = 1;
            int curBucket = buckets[0];

            for (int i = 1; i < len; i++) {
                if (buckets[i] - curBucket >= dis) {
                    ballNum++;
                    curBucket = buckets[i];
                }
                if (ballNum >= m) {
                    break;
                }
            }

            if (ballNum >= m) {
                answer = Math.max(answer, dis);
                left = dis + 1;
            } else {
                right = dis - 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 4, 9, 99999};
        int b = 2;
        System.out.println(solution(a, b));
    }
}