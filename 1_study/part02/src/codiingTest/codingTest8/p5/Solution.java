package codiingTest.codingTest8.p5;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 냅색 문제를 3차원 배열로 만들어 풀어보았지만 0 점이 나왔고
// 가방의 용량이 작은 것 부터 채우는 방법을 이용하여 풀이하였지만 0 점이 나왔습니다.
// 코드는 없습니다 ㅠㅠ
// 0 점입니다.
public class Solution {
    private static class BagState {
        int val;
        List<Integer> isUsed;

        public BagState(int val) {
            this.val = val;
            isUsed = new ArrayList<>();
        }
    }

    public static int solution(int N, int K1, int K2, int[] W, int[] V) {
        int answer = 0;
        int[][] items = new int[N + 1][2];
        boolean[] isUsed = new boolean[N + 1];
        if (K1 > K2) {
            int temp = K1;
            K1 = K2;
            K2 = temp;
        }

        for (int i = 0; i < N; i++) {
            items[i + 1] = new int[]{W[i], V[i]};
        }
        Arrays.sort(items, (x, y) -> x[0] - y[0]);

        BagState[][] bagState1 = new BagState[N + 1][K1 + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K1 + 1; j++) {
                bagState1[i][j] = new BagState(0);
            }
        }
        int[][] bagState2 = new int[N + 1][K2 + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < K1 + 1; j++) {
                if (j > items[i][0]) {
                    if (items[i][1] > bagState1[i - 1][j - items[i][0]].val + items[i][1]) {
                        bagState1[i][j] = new BagState(items[i][1]);
                        bagState1[i][j].isUsed.add(i);
                    } else {
                        bagState1[i][j] = new BagState(bagState1[i - 1][j - items[i][0]].val + items[i][1]);
                        bagState1[i][j].isUsed.add(i);
                        bagState1[i][j].isUsed.addAll(bagState1[i - 1][j - items[i][0]].isUsed);
                    }
                } else {
                    bagState1[i][j] = bagState1[i - 1][j];
                }
            }
        }

        for (int num : bagState1[N][K1].isUsed) {
            isUsed[num] = true;
        }


        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < K2 + 1; j++) {
                if (!isUsed[i]) {
                    if (j > items[i][0]) {
                        bagState2[i][j] = Math.max(items[i][1], bagState2[i - 1][j - items[i][0]] + items[i][1]);
                    } else {
                        bagState2[i][j] = bagState2[i - 1][0];
                    }
                }
            }
        }

        System.out.println(bagState1[N][K1].val);
        System.out.println(bagState2[N][K2]);
        return bagState1[N][K1].val + bagState2[N][K2];
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 4, 1, 5, 2, 5, 2, 3, 4, 2, 3, 4, 1, 3};
        int[] b = new int[]{7, 6, 3, 15, 18, 12, 7, 16, 11, 8, 13, 14, 7, 3};
        System.out.println(solution(14, 13, 18, a, b));
        //        기댓값 〉
        //        119

        a = new int[]{6, 4, 5, 6, 8, 9, 10, 3};
        b = new int[]{10, 4, 6, 8, 2, 8, 5, 6};
        System.out.println(solution(8, 10, 15, a, b));
        //        기댓값 〉
        //        34
    }
}

