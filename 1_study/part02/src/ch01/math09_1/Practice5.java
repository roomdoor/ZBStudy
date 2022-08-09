package ch01.math09_1;

public class Practice5 {
    public static int solution(int[][] grid) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += 4;
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (isChecked(grid, x, y) && grid[x][y] == 1) {
                            result--;
                        }
                    }
                }
            }
        }
        return result;
    }

    // 재귀 풀이
    public static int solution2(int[][] grid) {
        int[][] dir = {{1, 0, -1, 0}, {0, -1, 0, 1}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return recursion(grid, dir, i, j);
                }
            }
        }
        return 0;
    }

    public static int recursion(int[][] grid, int[][] directions, int i, int j) {
        int len = 0;
        grid[i][j]++;
        for (int k = 0; k < 4; k++) {
            int nextX = i + directions[0][k];
            int nextY = j + directions[1][k];

            if (!isChecked(grid, nextX, nextY) || grid[nextX][nextY] == 0) {
                len++;
            } else {
                if (grid[nextX][nextY] == 1) {
                    len += recursion(grid, directions, nextX, nextY);
                }
            }
        }
        return len;
    }

    public static boolean isChecked(int[][] grid, int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }

    public static void main(String[] args) {
        // Test code
        int[][] grid = {{1}};
        System.out.println(solution(grid));
        System.out.println(solution2(grid));
        System.out.println();


        grid = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        System.out.println(solution(grid));
        System.out.println(solution2(grid));
    }
}
