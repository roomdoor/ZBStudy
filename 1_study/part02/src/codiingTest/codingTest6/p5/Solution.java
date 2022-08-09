package codiingTest.codingTest6.p5;


import java.util.Arrays;

public class Solution {
    public static int[] solution(int[] scores) {
        int[] answer = new int[scores.length];
        Arrays.fill(answer, 1);

        for (int i = 1; i < answer.length; i++) {
            if (scores[i] > scores[i - 1]) {
                answer[i] = answer[i - 1] + 1;
            } else {
                answer[i] = 1;
                int k = i;
                while (k > 0) {
                    if (answer[k - 1] == answer[k] && scores[k - 1] > scores[k]) {
                        answer[k - 1] = answer[k] + 1;
                    } else {
                        break;
                    }
                    k--;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 4, 5, 5, 5, 1};
//        System.out.println(Arrays.toString(solution(a)));
        a = new int[]{5, 2, 5, 3, 4, 1, 6, 5, 2, 5, 2, 3};
        System.out.println(Arrays.toString(solution(a)));

    }
}
