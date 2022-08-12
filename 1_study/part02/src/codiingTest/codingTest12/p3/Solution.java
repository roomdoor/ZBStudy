package codiingTest.codingTest12.p3;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    private static class Num implements Comparable<Num> {
        int num;
        int binaryNum;
        int count;

        public Num(int num, int count) {
            this.num = num;
            this.binaryNum = getBinaryNum(num);
            this.count = count;
        }

        @Override
        public int compareTo(Num o) {
            return this.count - o.count;
        }
    }

    public static int solution(int[] status) {
        int allOneNum = 0;
        for (int i = 0; i < status.length - 1; i++) {
            allOneNum += 1;
            allOneNum *= 10;
        }
        allOneNum += 1;

        int[] dp = new int[(int) Math.pow(2, status.length)];
        Arrays.fill(dp, 100000);
        int num = arrToNum(status);
        dp[getBinaryNum(num)] = 0;
        if (num == allOneNum) {
            return 0;
        }

        PriorityQueue<Num> queue = new PriorityQueue<>();
        queue.add(new Num(num, 0));

        while (!queue.isEmpty()) {
            Num cur = queue.poll();
            int[] arr = numToAr(cur.num, status.length);
            for (int i = 0; i < arr.length; i++) {
                int[] temp = arr.clone();
                int next = changeSwitch(i, temp);
                int nextBinary = getBinaryNum(next);
                if (next == allOneNum) {
                    return cur.count + 1;
                }
                if (dp[nextBinary] > cur.count + 1) {
                    dp[nextBinary] = cur.count + 1;
                    queue.add(new Num(next, cur.count + 1));
                }
            }
        }
        return -1;
    }

    public static int getBinaryNum(int num) {
        return Integer.parseInt(String.valueOf(num), 2);
    }

    public static int[] numToAr(int num, int len) {
        int[] answer = new int[len];
        while (num > 0) {
            answer[len - 1] = num % 10;
            num /= 10;
            len--;
        }

        return answer;
    }

    public static int arrToNum(int[] status) {
        int answer = 0;
        for (int j : status) {
            answer *= 10;
            answer += j;
        }
        return answer;
    }

    public static int changeSwitch(int n, int[] status) {
        if (n == 0) {
            status[0] = inverter(status[0]);
            if (status.length > 1) {
                status[1] = inverter(status[1]);
            }
        } else if (n == status.length - 1) {
            status[status.length - 2] = inverter(status[status.length - 2]);
            status[status.length - 1] = inverter(status[status.length - 1]);
        } else {
            status[n - 1] = inverter(status[n - 1]);
            status[n] = inverter(status[n]);
            status[n + 1] = inverter(status[n + 1]);
        }

        return arrToNum(status);
    }

    public static int inverter(int n) {
        return n == 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 0, 1, 0, 1};
        System.out.println(solution(a));
        a = new int[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 1};
        System.out.println(solution(a));

        a = new int[]{1};
        System.out.println(solution(a));


    }
}
