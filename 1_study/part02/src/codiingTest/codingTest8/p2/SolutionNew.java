package codiingTest.codingTest8.p2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolutionNew {
    public static int solution(int depth, int n, int[][] blocks) {
        int len = blocks[0].length;
        int[][] dp = new int[2][len];

        System.arraycopy(blocks[0], 0, dp[0], 0, len);

        for (int i = 1; i < depth + 1; i++) {
            for (int j = 0; j < len; j++) {
                if (j == 0) {
                    dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j + 1], dp[(i - 1) % 2][j]) + blocks[i][j];
                } else if (j == len - 1) {
                    dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j - 1], dp[(i - 1) % 2][j]) + blocks[i][j];
                } else {
                    dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j + 1], dp[(i - 1) % 2][j - 1]);
                    dp[i % 2][j] = Math.min(dp[i % 2][j], dp[(i - 1) % 2][j]) + blocks[i][j];
                }

            }
        }

        return dp[depth % 2][n];
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

        for (int i = 1; i <= 5; i++) {
            System.out.print("문제 " + i + "번 acc Test 답 : ");
            fileReader(i, 0, a);
            System.out.println();
            System.out.print("문제 " + i + "번 eff Test 답 : ");
            fileReader(i, 1, a);
            System.out.println();
        }


    }
}
