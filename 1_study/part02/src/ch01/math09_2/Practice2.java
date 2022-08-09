package ch01.math09_2;

import java.sql.PreparedStatement;

public class Practice2 {
    public static int solution(String str) {
        char[] arr = str.toCharArray();
        int count = 0;
        return isPalindrome(0, arr.length - 1, arr, count);
    }

    public static int isPalindrome(int left, int right, char[] arr, int delCnt) {
        for (int i = 0; i < arr.length / 2; i++) {

            if (arr[left + i] != arr[right - i]) {
                if (arr[left + 1 + i] == arr[right - i]) {
                    left++;
                    delCnt += 1;
                    if (delCnt > 1) return 2;
                } else if (arr[left + i] == arr[right - 1 - i]) {
                    right--;
                    delCnt += 1;
                    if (delCnt > 1) return 2;
                } else {
                    return 2;
                }
            }
        }

        return delCnt;
    }

    public static void main(String[] args) {
        // Test code
        String[] str = {"abba", "summuus", "xabba", "xabbay", "comcom", "comwwmoc", "comwwtmoc"};
        System.out.println(solution("abba"));
        System.out.println(solution("summuus"));
        System.out.println(solution("xabba"));
        System.out.println(solution("xabbay"));
        System.out.println(solution("comcom"));
        System.out.println(solution("comwwmoc"));
        System.out.println(solution("comwwtmoc"));
    }
}
