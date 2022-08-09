package ch01.codeTestTest.p1;

public class Solution {
    public int solution(int n, int m) {
        int answer = 0;

        for (int i = n; i <= m; i++) {
            if (isPalindrome(i)) {
                answer += 1;
            }
        }
        return answer;
    }

    public static boolean isPalindrome(int num) {
        int temp = num;
        int len = 0;
        while (temp > 0) {
            temp /= 10;
            len++;
        }

        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = num % 10;
            num /= 10;
        }

        int left = 0;
        int right = len - 1;
        while (left < right) {
            if (arr[left] == arr[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }


}