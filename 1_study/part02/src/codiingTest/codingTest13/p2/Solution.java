package codiingTest.codingTest13.p2;

// 5번 풀다가 못풀었네요...
public class Solution {
    public static int solution(int[] arr, int target) {
        int answer = 0;
        int[] dp = new int[20001]; // 0 ~ 20001 (-1000 ~ 10000)

        int len = arr.length;
        int curNum = 0;
        for (int i = 0; i < len; i++) {
            curNum += arr[i];
            for (int j = 0; j < len; j++) {

                curNum += arr[j];

                for (int k = 0; k < len; k++) {

                    curNum += arr[k];
                    dp[curNum + 10000] = 3;
                    curNum -= arr[k];
                }
                curNum -= arr[j];
            }
            curNum -= arr[i];
        }

        int targetDistance = 0;
        while (true) {
            if (isPossible(target + 10000 - targetDistance) && dp[target + 10000 - targetDistance] != 0) {
                answer = target - targetDistance;
                break;
            }

            if (isPossible(target + 10000 + targetDistance) && dp[target + 10000 + targetDistance] != 0) {
                answer = target + targetDistance;
                break;
            }
            targetDistance++;
        }
        return answer;
    }

    public static boolean isPossible(int a) {
        return a >= 0 && a <= 20001;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 10, 12, 15};
        int target = 21;
        System.out.println(solution(arr, target));
    }
}












