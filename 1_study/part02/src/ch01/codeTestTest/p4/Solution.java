package ch01.codeTestTest.p4;

public class Solution {
    public static int solution(String dirs) {
        int[][][] isVisited = new int[11][11][4];
        int answer = 0;

        int x = 5;
        int y = 5;


        char[] arr = dirs.toCharArray();

        for (char dir : arr) {

            if (dir == 'D') {
                if (isPossible(x + 1, y)) {
                    if (isVisited[x][y][0] == 0) {
                        answer++;
                    }
                    isVisited[x][y][0] = 1;
                    x += 1;
                    isVisited[x][y][2] = 1;
                }
            } else if (dir == 'R') {
                if (isPossible(x, y + 1)) {
                    if (isVisited[x][y][1] == 0) {
                        answer++;
                    }
                    isVisited[x][y][1] = 1;
                    y += 1;
                    isVisited[x][y][3] = 1;
                }

            } else if (dir == 'U') {
                if (isPossible(x - 1, y)) {
                    if (isVisited[x][y][2] == 0) {
                        answer++;
                    }
                    isVisited[x][y][2] = 1;
                    x += -1;
                    isVisited[x][y][0] = 1;
                }
            } else {
                if (isPossible(x, y - 1)) {
                    if (isVisited[x][y][3] == 0) {
                        answer++;
                    }
                    isVisited[x][y][3] = 1;
                    y += -1;
                    isVisited[x][y][1] = 1;
                }
            }
            System.out.println("(" + x + ", " + y + ")\n이동 거리 : " + answer + "\n");
        }

        return answer;
    }


    public static boolean isPossible(int x, int y) {
        return x >= 0 && y >= 0 && x < 11 && y < 11;
    }
}
