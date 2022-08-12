package codiingTest.codingTest12.p2;

import java.util.Arrays;

public class Solution {
    public static int solution(int[] heights) {
        int[] temp = heights.clone();
        Arrays.sort(temp);
        int answer = temp[temp.length - 1];

        int bottomLen = 0;
        for (int i = 0; i < temp.length - 1; i++) {
            for (int j = 0; j < heights.length; j++) {
                if (heights[j] >= temp[i]) {
                    bottomLen++;
                } else {
                    answer = Math.max(answer, bottomLen * temp[i]);
                    bottomLen = 0;
                }
                if (j == heights.length - 1) {
                    answer = Math.max(answer, bottomLen * temp[i]);
                    bottomLen = 0;
                }
            }
        }

        return answer;
    }
}












