package codiingTest.codingTest8.p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int solution(int N, int[] rewards) {
        int answer = 0;
        int[] dp = new int[rewards.length];

        // 0 번째 방문
        dp[0] = rewards[0];
        dp[1] = 0;

        for (int i = 2; i < dp.length - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + rewards[i], dp[i - 1]);
        }
        answer = dp[rewards.length - 2];

        dp[0] = 0;
        dp[1] = rewards[1];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + rewards[i], dp[i - 1]);
        }
        answer = Math.max(answer, dp[rewards.length - 1]);

        return answer;
    }

    public static void fileReader(int pNum, int accOrEff, int[] a) throws IOException {
        String aOrE = accOrEff == 0 ? "acc" : "eff";
        String address = "/Users/isihwa/workspace/zerobase/강의자료/코테_답안/0714" +
                "/테스트케이스/problem3/" + aOrE + "_test/" + pNum + "_i.txt";

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(address));
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        Integer n = 0;
        List<Integer> rewardList = new ArrayList<>();

        line = sb.toString();
        sb = new StringBuilder();
        boolean start = false;

        for (int i = 0; i < line.length(); i++) {
            char next = line.charAt(i);
            switch (next) {
                case ' ':
                    break;

                case ',':
                    if (n == 0) {
                        n = Integer.parseInt(sb.toString());
                    }

                    if (start && !sb.toString().equals("")) {
                        rewardList.add(Integer.valueOf(sb.toString()));
                    }

                    sb = new StringBuilder();
                    break;

                case '[':
                    if (start) {
                        rewardList = new ArrayList<>();
                    } else {
                        start = true;
                    }
                    break;

                case ']':
                    rewardList.add(Integer.valueOf(sb.toString()));
                    sb = new StringBuilder();
                    break;

                default:
                    sb.append(next);
                    break;

            }
        }

        int[] reward = rewardList.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(solution(n, reward));

        address = "/Users/isihwa/workspace/zerobase/강의자료/코테_답안/0714" +
                "/테스트케이스/problem3/" + aOrE + "_test/" + pNum + "_o.txt";
        br = new BufferedReader(new FileReader(address));
        line = br.readLine();
        System.out.println("문제 " + pNum + "번 " + aOrE + " 답지 답 : " + line);
    }

    public static void main(String[] args) throws IOException {
        int[] a = new int[]{75, 25, 70, 46, 60, 7, 85, 65, 28, 78};
//        System.out.println(solution(10, a));

        for (int i = 1; i <= 5; i++) {
            System.out.print("문제 " + i + "번 acc Test 답 : ");
            fileReader(i, 0, a);
            System.out.println();

        }

    }
}

