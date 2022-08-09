package codiingTest.codingTest8.p2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static int solution(int depth, int n, int[][] blocks) {
        int answer = 0;
        int[][] dp = new int[blocks.length][blocks[0].length];
        dp[depth][n] = blocks[depth][n];
        cur(depth, n, blocks, dp);
        answer = Arrays.stream(dp[0]).min().getAsInt() + blocks[depth][n];

        return answer;
    }

    public static void cur(int depth, int n, int[][] blocks, int[][] dp) {
        if (depth == 0) {
            return;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        queue.add(new int[]{depth, n});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = -1; i <= 1; i++) {
                if (isPossible(cur[0], cur[1] + i, blocks[0].length)) {
                    if (dp[cur[0] - 1][cur[1] + i] == 0 || dp[cur[0] - 1][cur[1] + i] > dp[cur[0]][cur[1]] + blocks[cur[0] - 1][cur[1] + i]) {
                        dp[cur[0] - 1][cur[1] + i] = dp[cur[0]][cur[1]] + blocks[cur[0] - 1][cur[1] + i];
                        queue.add(new int[]{cur[0] - 1, cur[1] + i});
                    }
                }
            }
        }
    }

    public static boolean isPossible(int depth, int n, int len) {    // 좌표가 DP 사이즈 안에 있는지 확인하는 메소드
        return n < len && n >= 0 && depth > 0;
    }

    public static void fileReader(int pNum, int accOrEff, int[][] a) throws IOException {
        String aOrE = accOrEff == 0 ? "acc" : "eff";
        String address = "/Users/isihwa/workspace/zerobase/강의자료/코테_답안/0714" +
                "/테스트케이스/problem2/" + aOrE + "_test/" + pNum + "_i.txt";

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(address));
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        Integer depth = 0;
        Integer n = 0;
        List<List<Integer>> blocks = new ArrayList<>();
        List<Integer> blockLine = new ArrayList<>();

        line = sb.toString();
        sb = new StringBuilder();
        boolean start = false;

        for (int i = 0; i < line.length(); i++) {
            char next = line.charAt(i);
            switch (next) {
                case ' ':
                    break;

                case ',':
                    if (depth == 0) {
                        depth = Integer.parseInt(sb.toString());
                    } else if (n == 0) {
                        n = Integer.parseInt(sb.toString());
                    }

                    if (start && !sb.toString().equals("")) {
                        blockLine.add(Integer.valueOf(sb.toString()));
                    }

                    sb = new StringBuilder();
                    break;

                case '[':
                    if (start) {
                        blockLine = new ArrayList<>();
                    } else {
                        start = true;
                    }
                    break;

                case ']':
                    if (blocks.size() <= depth) {
                        blockLine.add(Integer.valueOf(sb.toString()));
                        sb = new StringBuilder();
                        blocks.add(blockLine);
                    }
                    break;

                default:
                    sb.append(next);
                    break;

            }
        }

        int[][] aa = new int[blocks.size()][blocks.get(0).size()];
//        System.out.println(blocks.size());
//        for (int i = 0; i < blocks.size(); i++) {
//            System.out.println(blocks.get(i).size());
//        }
//
//        String t = blocks.get(blocks.size() - 1).toString();
//        String t9 = "[9, 10, 18, 81, 92, 91, 80, 51, 37, 82, 7, 81, 99, 8, 92, 17, 84, 72, 15, 64, 56, 83, 35, 99, 15, 100, 63, 43, 98, 20, 92, 44, 57, 74, 78, 93, 31, 42, 73, 19, 24, 81, 57, 55, 95, 33, 85, 5, 71, 81, 30, 26, 5, 24, 58, 62, 42, 72, 70, 38, 15, 59, 36, 46, 36, 33, 54, 32, 53, 44, 27, 77, 88, 44, 3, 68, 24, 41, 30, 14, 53, 97, 77, 68, 89, 100, 76, 65, 64, 23, 83, 43, 33, 31, 99, 68, 5, 3, 90, 47]";
//        String t10 = "[28, 55, 67, 92, 14, 33, 23, 60, 2, 94, 36, 18, 86, 69, 51, 73, 88, 100, 37, 69, 9, 71, 14, 37, 92, 18, 74, 71, 93, 1, 24, 64, 11, 32, 3, 47, 34, 99, 34, 89, 64, 9, 29, 63, 60, 81, 53, 20, 26, 36, 70, 75, 53, 82, 72, 82, 90, 80, 82, 44, 89, 23, 53, 63, 87, 15, 32, 47, 49, 66, 60, 13, 4, 36, 56, 62, 41, 48, 90, 78, 75, 63, 92, 23, 42, 17, 91, 86, 76, 98, 16, 40, 20, 79, 72, 72, 84, 71, 70, 54]";
//        if (t.equals(t10)) {
//            System.out.println("Yes");
//
//        } else {
//            System.out.println("No");
//        }
//
//        System.out.println(t);
//        System.out.println(t9);
//        System.out.println();
//        System.out.println(t);
//        System.out.println(t10);

        for (int i = 0; i < aa.length; i++) {
            for (int j = 0; j < aa[0].length; j++) {
                int input = blocks.get(i).get(j);
                aa[i][j] = input;
            }
        }

        System.out.println(solution(depth, n, aa));

        address = "/Users/isihwa/workspace/zerobase/강의자료/코테_답안/0714" +
                "/테스트케이스/problem2/" + aOrE + "_test/" + pNum + "_o.txt";
        br = new BufferedReader(new FileReader(address));
        line = br.readLine();
        System.out.println(line);
    }

    public static void main(String[] args) throws IOException {
        int[][] a = new int[][]{{5, 6, 2, 6}, {1, 6, 4, 9}, {5, 6, 9, 4}, {55, 14, 21, 14}};
        System.out.println(solution(3, 3, a));

//        for (int i = 1; i <= 5; i++) {
//            System.out.print("문제 " + i + "번 acc Test 답 : ");
//            fileReader(i, 0, a);
//            System.out.println();
//            System.out.print("문제 " + i + "번 eff Test 답 : ");
//            fileReader(i, 1, a);
//            System.out.println();
//        }


    }
}
