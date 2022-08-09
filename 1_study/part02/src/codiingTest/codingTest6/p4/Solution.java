package codiingTest.codingTest6.p4;

import java.util.Arrays;

public class Solution {
    public static int[] solution(int[] arr, int k) {
        int[] answer = new int[arr.length - k + 1];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            max = Math.max(max, arr[i]);
        }

        int start = 0;
        int end = k - 1;
        int j = 0;
        while (end < arr.length) {
            answer[j++] = max;
            start++;
            end++;
            if (end == arr.length) {
                return answer;
            }

            if (arr[start - 1] == max) {
                max = Integer.MIN_VALUE;
                for (int i = start; i <= end; i++) {
                    max = Math.max(max, arr[i]);
                }
            } else {
                max = Math.max(max, arr[end]);
            }


        }


        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 2, 6, 4, 2, 3};
        System.out.println(Arrays.toString(solution(a, 3)));
        a = new int[]{75, 25, 70, 46, 60, 7, 85, 65, 28, 78, 29, 37};
        System.out.println(Arrays.toString(solution(a, 5)));
    }
}

//[75, 25, 70, 46, 60, 7, 85, 65, 28, 78, 29, 37], 5
