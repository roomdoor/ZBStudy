package src;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Practice3 {
    public static String solution(String input, String cmd) {
        StringBuilder result = new StringBuilder(input);
        int num = input.length();

        List<String> cmdd = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(cmd);
        while (st.hasMoreElements()) {
            cmdd.add(st.nextToken());
        }

        for (int i = 0; i < cmdd.size(); i++) {
            switch (cmdd.get(i)) {
                case "L":
                    if (num > 0) {
                        num -= 1;
                    }
                    break;

                case "D":
                    if (num < result.length()) {
                        num += 1;
                    }
                    break;

                case "B":
                    if (num == 0) break;
                    result.deleteCharAt(num - 1);
                    num--;
                    break;

                case "P":
                    result.insert(num, cmdd.get(i + 1));
                    i++;
                    num++;
                    break;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // test code
        System.out.println(solution("aba", "L B"));
        System.out.println(solution("abcd", "P x L P y"));
        System.out.println(solution("abc", "L L L P x L B P y"));
        System.out.println(solution("a", "B B L L D D P a P b P c"));
    }
}
