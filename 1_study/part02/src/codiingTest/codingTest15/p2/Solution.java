package codiingTest.codingTest15.p2;


import java.util.Arrays;

public class Solution {
    public int solution(int[] buckets, int m) {
        int answer = 0;
        int len = buckets.length;
        Arrays.sort(buckets);
        int[] interval = new int[len - 1];
        for (int i = 1; i < len; i++) {
            interval[i] = buckets[i - 1] - buckets[i];
        }


        return answer;
    }
}

// 0    0   0   0   0   0   0   0   0   0
// 1                1                   1