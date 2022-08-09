package codiingTest.codingTest6.p2;

import java.util.Arrays;

public class Solution {
    public static int solution(int[] amount, int[] value, int[] stomach) {
        int answer = 0;
        int peopleNum = stomach.length;
        int totalNum = 0;

        int[][] meet = new int[amount.length][2];
        for (int i = 0; i < amount.length; i++) {
            meet[i][0] = amount[i];
            meet[i][1] = value[i];
        }

        Arrays.sort(meet, (x, y) -> -x[1] + y[1]);

        if (meet[0][0] >= peopleNum) {
            answer += (meet[0][0] / peopleNum) * meet[0][1] * peopleNum;
        }
        for (int i = 0; i < peopleNum; i++) {
            stomach[i] -= (meet[0][0] / peopleNum);
            totalNum += stomach[i];
        }


        for (int i = 1; i < amount.length; i++) {
            if (totalNum == 0) {
                break;
            }
            if (totalNum >= meet[i][0]) {
                answer += meet[i][0] * meet[i][1];
                totalNum -= meet[i][0];
            } else {
                answer += totalNum * meet[i][1];
                totalNum = 0;
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{10, 1, 1, 1};
        int[] b = new int[]{10, 1, 1, 1};
        int[] c = new int[]{1, 2, 2, 2};
//        System.out.println(solution(a, b, c));

        a = new int[]{11, 17};
        b = new int[]{4, 8};
        c = new int[]{5, 19, 16, 5, 4, 4, 2, 15, 20, 19, 20, 20, 3, 9, 13, 5, 2, 4, 10, 17, 2, 4, 10, 10, 8, 6, 13, 18, 13, 5, 11, 2, 8, 10, 13, 8, 16, 8, 7, 14, 5, 20, 6, 16, 15, 16, 7, 9, 1, 7, 11, 2, 8, 10, 17, 9, 2, 13, 5, 9, 19, 20, 12, 15, 20, 9, 2, 15, 10, 3, 6, 1, 12, 16, 2, 13, 2, 12, 2, 8, 1, 13, 5, 16, 13, 4, 6, 14, 15, 9, 12, 7, 20, 10, 13, 5, 6, 17, 16, 10};

//        System.out.println(solution(a, b, c));

        a = new int[]{7, 6, 3, 15, 18, 12, 7, 16, 11};
        b = new int[]{20, 18, 2, 12, 8, 7, 17, 19, 15};
        c = new int[]{8, 13, 14, 7, 3, 14};

        System.out.println(solution(a, b, c));
    }
}
