package codiingTest.codingTest14.p4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 이번 윷놀이 문제는 DP 문제입니다.
 * 따져줘야 하는 경우의 수가 매우 많기 때문에, 이것을 BFS로 하기에는 적절하지 않습니다.
 * 따져줘야 하는 종류는 아래와 같습니다.
 *
 * 1. 시작점에서 출발하여 도개걸
 * 2. 숏컷을 타고 도개걸
 * 3. 시작점에서 출발하여 윷모 + 도개걸윷모
 * 4. 숏컷을 타고 윷모 + 도개걸윷모
 * 5. 시작점에서 출발할아여 윷모 + 숏컷을 타고 도개걸윷모
 * 6. 숏컷을 타고 윷모 + 숏컷을 타고 도개걸윷모
 *
 * 아주 다양한 경우의 수가 있고, 코드에서 복잡한 숫자들과 for, if문은 모두 위를 커버하기 위함입니다.
 */

public class Solution {
    public int solution(int N, int[][] edges) {
        int[] dp = new int[N + 1];
        // 1 -> N으로 숏컷 없이 가능 경우 N-1이므로, N이면 충분히 큰 숫자
        Arrays.fill(dp, N);
        dp[0] = 0;

        // 0: 1번 던진 경우, 4: 윷으로 2번 던진 경우, 5: 모로 2번 던진 경우
        List<Integer> firstMoves = List.of(0, 4, 5);
        // 1번 던진 경우 도개걸 중 하나
        List<Integer> secondMoves1 = List.of(1, 2, 3);
        // 2번 던진 경우 윷모 이후에 도개걸윷모 모두 가능
        List<Integer> secondMoves2 = List.of(1, 2, 3, 4, 5);

        for (int i = 0; i < N; i++) {
            int finalI = i;
            // 현재 위치에서 사용 가능한 숏컷을 모두 찾음
            List<Integer> shortcuts = new ArrayList<>(Arrays.stream(edges)
                    .filter(x -> x[0] == finalI)
                    .mapToInt(x -> x[1] - 1) // 숏컷을 타고 이동하는 데에 1회 이동하므로 -1
                    .boxed()
                    .collect(Collectors.toList()));
            // 숏컷을 쓰지 않는 경우도 추가
            shortcuts.add(i);

            for (int start : shortcuts) {
                for (int move1 : firstMoves) {
                    int j = start + move1;
                    List<Integer> shortcuts2 = new ArrayList<>();
                    if (move1 > 0) { // 1번 던지는 경우, 숏컷을 2번 탈 수 없다.
                        // 윷모 이후에 2번째 던질 때 타고 갈 수 있는 숏컷을 모두 찾는다.
                        shortcuts2.addAll(Arrays.stream(edges)
                                .filter(x -> x[0] == j)
                                .mapToInt(x -> x[1] - 1) // 숏컷으로 이동하는 데에 1회 이동하므로 -1
                                .boxed()
                                .collect(Collectors.toList()));
                    }
                    // 숏컷을 쓰지 않는 경우도 추가
                    shortcuts2.add(j);

                    for (int start2 : shortcuts2) {
                        // 1번 던지는 경우 도개걸만 가능 (만약 윷모가 나온다면, 1번 던지는 것이 모순)
                        // 2번 던지는 경우 도개걸윷모 모두 가능
                        List<Integer> secondMoves = move1 == 0 ? secondMoves1 : secondMoves2;
                        for (int move2 : secondMoves) {
                            int k = start2 + move2;
                            if (k <= N) {
                                dp[k] = Math.min(dp[k], dp[i] + 1);
                            }
                        }
                    }
                }
            }
        }
        return dp[N];
    }
}