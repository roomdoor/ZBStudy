package codiingTest.codingTest14.p4;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static final int INF = 1000;
    static int[] dp;

    public static int solution(int N, int[][] edges) {
        int answer = 100000;

        dp = new int[N + 11];
        Arrays.fill(dp, INF);
        dp[1] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        queue.add(new int[]{1, 0}); // cur Point, turn

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int point = cur[0];
            int turn = cur[1];

            if (point >= N) {
                answer = Math.min(turn, answer);
                continue;
            }

            int shorCut = point;
            boolean isShorCut = false;
            for (int[] ints : edges) {
                if (ints[0] == point) {
                    shorCut = ints[1];
                    isShorCut = true;
                    break;
                }
            }

            for (int i = 1; i <= 3; i++) {       //도, 개, 걸
                if (isShorCut) {
                    if (dp[shorCut - 1 + i] > turn + 1) {
                        dp[shorCut - 1 + i] = turn + 1;
                        queue.add(new int[]{shorCut - 1 + i, turn + 1});
                    }
                }

                if (dp[point + i] > turn + 1) {
                    dp[point + i] = turn + 1;
                    queue.add(new int[]{point + i, turn + 1});
                }
            }

            for (int i = 4; i <= 5; i++) {       // 윳, 모
                int oneTurnPoint = point;
                if (isShorCut) {
                    oneTurnPoint = shorCut - 1 + i;
                    for (int j = 1; j <= 5; j++) {   // 도 ~ 모
                        isShorCut = false;
                        for (int[] ints : edges) {
                            if (ints[0] == oneTurnPoint) {
                                oneTurnPoint = ints[1];
                                isShorCut = true;
                                break;
                            }
                        }

                        if (isShorCut) {
                            if (dp[oneTurnPoint + j - 1] > turn + 1) {
                                dp[oneTurnPoint + j - 1] = turn + 1;
                            }
                        } else {
                            if (dp[oneTurnPoint + j] > turn + 1) {
                                dp[oneTurnPoint + j] = turn + 1;
                            }
                        }
                    }
                }

                oneTurnPoint += i;
                for (int j = 1; j <= 5; j++) {   // 도 ~ 모
                    isShorCut = false;
                    for (int[] ints : edges) {
                        if (ints[0] == oneTurnPoint) {
                            oneTurnPoint = ints[1];
                            isShorCut = true;
                            break;
                        }
                    }

                    if (isShorCut) {
                        if (dp[oneTurnPoint + j - 1] > turn + 1) {
                            dp[oneTurnPoint + j - 1] = turn + 1;
                        }
                    } else {
                        if (dp[oneTurnPoint + j] > turn + 1) {
                            dp[oneTurnPoint + j] = turn + 1;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int N = 34;
        int[][] e = new int[][]{{1, 4}, {6, 12}, {15, 24}};
        System.out.println(solution(N, e));
    }
}

