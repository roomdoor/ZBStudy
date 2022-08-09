package ch01.math09_1;

import java.util.ArrayList;

public class Practice3 {
    // # 1 기본 permutation 방법
    public static boolean solution(String s1, String s2) {
        char[] arr = s1.toCharArray();
        char[] out = new char[s1.length()];
        boolean[] visited = new boolean[s1.length()];
        ArrayList<String> list = new ArrayList<>();

        permutation(arr, 0, arr.length, arr.length, visited, out, list);

        for (String s : list) {
            if (s2.contains(s)) {
                return true;
            }
        }

        return false;
    }

    public static void permutation(char[] arr, int depth, int n, int r, boolean[] visited, char[] out, ArrayList<String> list) {
        if (depth == r) {
            list.add(new String(out));
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                permutation(arr, depth + 1, n, r, visited, out, list);
                visited[i] = false;
            }
        }
    }


    // # 2 문제 규칙 찾아 해결
    public static boolean solution2(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            arr.add(String.valueOf(s1.charAt(i)));
        }

        ArrayList<String> checkedArr = new ArrayList<>(arr);

        for (int i = 0; i < s2.length(); i++) {
            String s = String.valueOf(s2.charAt(i));
            if (checkedArr.contains(s)) {
                checkedArr.remove(s);
            } else {
                checkedArr = new ArrayList<>(arr);
            }

            if (checkedArr.size() == 0) {
                return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {
        // Test code
        String s1 = "ab";
        String s2 = "adbak";
        System.out.println(solution(s1, s2));
        System.out.println(solution2(s1, s2));
        System.out.println();

        s1 = "ac";
        s2 = "car";
        System.out.println(solution(s1, s2));
        System.out.println(solution2(s1, s2));
        System.out.println();

        s1 = "ak";
        s2 = "aabbkk";
        System.out.println(solution(s1, s2));
        System.out.println(solution2(s1, s2));
    }
}
