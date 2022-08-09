package ch03.NonLinearDS13_1;

public class Practice1 {

    public static boolean solution(char[][] board, String word) {
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    result = dfs(1, isVisited, board, word, i, j, dx, dy);
                    if (result) {
                        return true;
                    }
                }
            }
        }

        return result;
    }

    public static boolean dfs(int dp, boolean[][] isVisited, char[][] board, String word, int x, int y, int[] dx, int[] dy) {
        if (dp == word.length()) {
            return true;
        }

        boolean result = false;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (isPossible(nextX, nextY, isVisited, word.charAt(dp), board)) {
                isVisited[nextX][nextY] = true;
                result = dfs(dp + 1, isVisited, board, word, nextX, nextY, dx, dy);
                isVisited[nextX][nextY] = false;
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isPossible(int x, int y, boolean[][] isVisited, char word, char[][] board) {
        return x >= 0 && y >= 0 && x < isVisited.length && y < isVisited[0].length && !isVisited[x][y]
                && word == board[x][y];
    }

    public static void main(String[] args) {
        // Test code
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(solution(board, "ABCCED"));
        System.out.println(solution(board, "SEE"));
        System.out.println(solution(board, "ABCB"));
    }
}
