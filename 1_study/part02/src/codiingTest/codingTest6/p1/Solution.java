package codiingTest.codingTest6.p1;

import java.util.Arrays;

public class Solution {
    public static int[] solution(int[] values) {
        int x = 0;
        int y = 1;
        int[] answer = {0, 0};
        if (values == null || values.length < 2) {
            return answer;
        }

        int[] max = new int[2];
        int maxLng = 0;

        while (x < values.length && y < values.length) {
            if (values[y - 1] < values[y]) {
                y++;
                if (y == values.length) {
                    if (maxLng < y - x) {
                        max[0] = x;
                        max[1] = y - 1;
                        maxLng = y - x;
                    }
                }
            } else {
                if (maxLng < y - x) {
                    max[0] = x;
                    max[1] = y - 1;
                    maxLng = y - x;
                }
                x = y++;
            }
        }

        answer = max;

        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2};
        System.out.println(Arrays.toString(solution(a)));
    }
}
