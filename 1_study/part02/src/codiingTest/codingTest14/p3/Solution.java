package codiingTest.codingTest14.p3;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static boolean[][] isVisited;
    static int[][] maze;

    public static int solution(int[][] maze) {
        int answer = -1;
        Solution.maze = maze;
        int xLen = maze.length;
        int yLen = maze[0].length;
        isVisited = new boolean[xLen][yLen];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0, 0, 0}); // (x, y, turn, time(0 ~ 9), stop num

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int turn = cur[2];
            int time = cur[3];
            int stopNum = cur[4];

            if (x == xLen - 1 && y == yLen - 1) {
                answer = turn;
                break;
            }

            if (time >= 5) {
                if (!noMonster(x, y)) {
                    isVisited[x][y] = false;
                    continue;
                }
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (isPossible(newX, newY) && maze[newX][newY] != 1 && !isVisited[newX][newY]) {
                    if ((time + 1) % 10 < 5) {
                        isVisited[newX][newY] = true;
                        queue.add(new int[]{newX, newY, turn + 1, (time + 1) % 10, 0});
                    } else {
                        if (noMonster(newX, newY)) {
                            isVisited[newX][newY] = true;
                            queue.add(new int[]{newX, newY, turn + 1, (time + 1) % 10, 0});
                        }
                    }

                }
            }
            if (stopNum < 5) {
                queue.add(new int[]{x, y, turn + 1, (time + 1) % 10, stopNum + 1});
            }
        }

        return answer;
    }

    public static boolean isPossible(int x, int y) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length;
    }

    public static boolean noMonster(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (isPossible(newX, newY) && (maze[newX][newY] == 2 || maze[x][y] == 2)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0}, {0, 1, 0, 2, 0, 0}, {1, 1, 0, 1, 0, 1}, {0, 0, 0, 0, 1, 0}, {1, 1, 1, 0, 2, 0}};

        System.out.println(solution(a));
    }
}
