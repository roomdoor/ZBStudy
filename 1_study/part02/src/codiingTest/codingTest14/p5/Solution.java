package codiingTest.codingTest14.p5;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 이 문제는 DFS로 탐색하는 문제입니다.
 * 탐색 아이디어 자체는 간단하지만, 조건에 맞게 구현하는 난이도가 상당한 문제입니다.
 * 각 돌을 상하좌우로 치는 경우를 모두 따져보되, 다른 돌과 부딛히면 연쇄적으로 처리하는 것이 핵심입니다.
 * board를 매번 만들어서 dfs하도록 구현하였지만, board를 사용하지 않을 경우 더 효율적인 구현이 됩니다.
 * 단, 난이도가 높아져서 구현하는 시간이 더 오래 걸리니, 메모리가 부족한 경우 구현을 고려해볼 수 있습니다.
 */


public class Solution {
    int result;

    public int solution(int[][] enemies, int[][] players) {
        result = Integer.MAX_VALUE;

        // 전체 돌의 x, y 최소/최대값을 구해서 돌이 있는 영역만 board로 구성합니다.
        List<int[]> stones = new ArrayList<>();
        stones.addAll(Arrays.asList(enemies));
        stones.addAll(Arrays.asList(players));

        int minX = stones.stream().mapToInt(x -> x[0]).min().getAsInt();
        int maxX = stones.stream().mapToInt(x -> x[0]).max().getAsInt();
        int minY = stones.stream().mapToInt(x -> x[1]).min().getAsInt();
        int maxY = stones.stream().mapToInt(x -> x[1]).max().getAsInt();

        // 최소값을 빼 주어, 가장 작은 x, y값이 0이 되도록 합니다.
        // 이 과정이 없으면 과도하게 큰 board를 만들 가능성이 있습니다.
        enemies = Arrays.stream(enemies)
                .map(pos -> new int[]{pos[0] - minX, pos[1] - minY})
                .toArray(int[][]::new);

        players = Arrays.stream(players)
                .map(pos -> new int[]{pos[0] - minX, pos[1] - minY})
                .toArray(int[][]::new);

        // 정확히 돌이 있는 영역의 너비와 높이
        int W = maxX - minX + 1;
        int H = maxY - minY + 1;

        int[][] board = new int[H][W];

        // 적의 돌의 위치를 보드에 기록
        Arrays.stream(enemies).forEach(pos -> {
            board[pos[1]][pos[0]] = 1;
        });

        // 내 돌의 위치를 보드에 기록
        Arrays.stream(players).forEach(pos -> {
            board[pos[1]][pos[0]] = 2;
        });

        // dfs
        dfs(0, board);
        return result;
    }

    void dfs(int count, int[][] board) {
        int H = board.length;
        int W = board[0].length;
        List<int[]> enemyList = new ArrayList<>();
        List<int[]> playerList = new ArrayList<>();

        // board에서 상대 돌과 내 돌의 위치를 찾습니다.
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] == 1) {
                    enemyList.add(new int[]{j, i});
                } else if (board[i][j] == 2) {
                    playerList.add(new int[]{j, i});
                }
            }
        }

        // 적이 없으면 종료. 기존보다 좋은 결과면 업데이트.
        if (enemyList.isEmpty()) {
            result = Math.min(result, count);
            return;
        }

        // 내 돌이 없으면 종료.
        if (playerList.isEmpty()) {
            return;
        }

        // 이미 찾은 결과보다 오래걸리는 케이스는 더 조사할 필요가 없다.
        if (count >= result) {
            return;
        }

        for (int[] player : playerList) {
            // 네 방향에 대해서 돌을 친 결과를 계산합니다.
            // direction 1
            int[][] newBoard = deepCopy(board);
            int currX = player[0];
            int currY = player[1];
            int currStone = newBoard[currY][currX];

            // while문으로 연쇄적으로 부딛히는 것을 처리합니다.
            // currStone이 0이면 연쇄가 끝난 것입니다.
            while (currStone > 0) {
                newBoard[currY][currX] = 0; // 친 돌은 원래 위치에서 사라짐
                // 밖으로 나가거나, 부딛힐 때 까지 한칸씩 이동
                while (currY - 1 >= 0 && newBoard[currY - 1][currX] == 0) {
                    currY--;
                }
                // 밖으로 나갔으면 종료
                if (currY == 0) {
                    break;
                } else { // 부딛힌 경우, 현재 돌을 보드에 놓고 다른 돌이 currStone이 됩니다.
                    newBoard[currY][currX] = currStone;
                    currStone = newBoard[currY - 1][currX];
                    currY--;
                }
            }
            // 현재 결과에서 이어서 DFS합니다.
            dfs(count + 1, newBoard);

            // direction 2
            newBoard = deepCopy(board);
            currX = player[0];
            currY = player[1];
            currStone = newBoard[currY][currX];

            while (currStone > 0) {
                newBoard[currY][currX] = 0;
                while (currY + 1 < H && newBoard[currY + 1][currX] == 0) {
                    currY++;
                }
                if (currY == H - 1) {
                    break;
                } else {
                    newBoard[currY][currX] = currStone;
                    currStone = newBoard[currY + 1][currX];
                    currY++;
                }
            }
            dfs(count + 1, newBoard);

            // direction 3
            newBoard = deepCopy(board);
            currX = player[0];
            currY = player[1];
            currStone = newBoard[currY][currX];

            while (currStone > 0) {
                newBoard[currY][currX] = 0;
                while (currX - 1 >= 0 && newBoard[currY][currX - 1] == 0) {
                    currX--;
                }
                if (currX == 0) {
                    break;
                } else {
                    newBoard[currY][currX] = currStone;
                    currStone = newBoard[currY][currX - 1];
                    currX--;
                }
            }
            dfs(count + 1, newBoard);

            // direction 4
            newBoard = deepCopy(board);
            currX = player[0];
            currY = player[1];
            currStone = newBoard[currY][currX];

            while (currStone > 0) {
                newBoard[currY][currX] = 0;
                while (currX + 1 < W && newBoard[currY][currX + 1] == 0) {
                    currX++;
                }
                if (currX == W - 1) {
                    break;
                } else {
                    newBoard[currY][currX] = currStone;
                    currStone = newBoard[currY][currX + 1];
                    currX++;
                }
            }
            dfs(count + 1, newBoard);
        }
    }

    int[][] deepCopy(int[][] src) {
        int[][] dst = new int[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0,
                    dst[i], 0, dst[i].length);
        }
        return dst;
    }
}


