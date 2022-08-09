import java.util.ArrayList;
import java.util.List;

public class Practice4 {
    public static boolean[][] isVisited;

    public static ArrayList<Integer> solution(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        isVisited = new boolean[matrix.length][matrix[0].length];
        int x = 0;
        int y = 0;
        int[] dir;
        isVisited[0][0] = true;
        result.add(matrix[0][0]);
        for (int i = 0; true; i++) {
            dir = direction(x, y);
            if (dir[0] == -2) break;
            x = dir[0];
            y = dir[1];

            result.add(matrix[x][y]);
        }

        return result;
    }

    public static int[] direction(int x, int y) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        boolean[] dxdy = new boolean[4];
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextY >= 0 && nextX < isVisited.length && nextY < isVisited[0].length) {
                dxdy[i] = !isVisited[nextX][nextY];
            }
        }

        for (int i = 0; i < 4; i++) {
            if (dxdy[i]) {
                if (i == 0 && dxdy[3]) {
                    x += dx[3];
                    y += dy[3];
                    isVisited[x][y] = true;
                    return new int[]{x, y};
                } else {
                    x += dx[i];
                    y += dy[i];
                    isVisited[x][y] = true;
                    return new int[]{x, y};
                }
            }
        }
        return new int[]{-2};
    }

    public static void main(String[] args) {
        // Test code
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println((solution(matrix)));

        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println((solution(matrix)));
    }
}
