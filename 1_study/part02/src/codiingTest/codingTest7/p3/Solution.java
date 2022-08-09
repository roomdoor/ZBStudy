package codiingTest.codingTest7.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static String[] solution(String s) {
        List<String> answer = new ArrayList<>();
        StringBuilder sb;

        List<int[]> list = divider(s);


        for (int[] dot : list) {

            sb = new StringBuilder();
            for (int i = 1; i < 4; i++) {
                String substring = s.substring(dot[i - 1], dot[i]);
                sb.append(substring).append(".");
            }
            sb.append(s.substring(dot[3], s.length()));
            answer.add(sb.toString());
        }


        return answer.toArray(new String[0]);
    }


    public static List<int[]> divider(String s) {
        int len = s.length();

        List<int[]> dotList = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            for (int j = i + 1; j < i + 4; j++) {
                if (len - i < 3 || len - i > 9 || isChecked(0, i, s)) {
                    break;
                }

                for (int k = j + 1; k < j + 4; k++) {
                    if (len - j < 2 || len - j > 6 || isChecked(i, j, s)) {
                        break;
                    }

                    if (!(len - k > 3 || len <= k || isChecked(j, k, s) || isChecked(k, s.length(), s))) {
                        dotList.add(new int[]{0, i, j, k});
                    }
                }
            }
        }

        return dotList;
    }

    public static boolean isChecked(int start, int end, String s) {

        String substring = s.substring(start, end);
        boolean result1 = (substring.length() > 1 && substring.startsWith("0"));
        boolean result2 = (Integer.parseInt(substring) > 255);

        return result1 || result2;
    }

    public static void main(String[] args) {
        String a = "130988245";
        System.out.println(Arrays.toString(solution(a)));


    }
}

