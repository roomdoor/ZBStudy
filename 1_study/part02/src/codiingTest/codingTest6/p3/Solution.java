package codiingTest.codingTest6.p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static int solution(String[] ingredients, String[] items) {
        if (ingredients.length == items.length) {
            return items.length;
        }

        int answer = items.length;


        Set<String> set = Arrays.stream(ingredients).collect(Collectors.toSet());

        HashMap<String, Integer> map = new HashMap<>();

        int start = 0;
        int end = 1;
        map.put(items[start], map.getOrDefault(items[start], 0) + 1);
        map.put(items[end], map.getOrDefault(items[end], 0) + 1);

        while (start < end) {


            if (map.keySet().containsAll(set)) {
                answer = Math.min(answer, end - start);
                if (map.get(items[start]) == 1) {
                    map.remove(items[start]);
                } else {
                    map.put(items[start], map.get(items[start]) - 1);
                }
                start++;
            } else {
                if (end < items.length - 1) {
                    end++;
                } else {
                    break;
                }
                map.put(items[end], map.getOrDefault(items[end], 0) + 1);
            }


        }

        return answer + 1;
    }

    public static int answerReader(int problemNum, int accOrEff, int tcNum) throws IOException {
        int answer = 0;
        String acOrEc = accOrEff == 0 ? "/acc_test" : "/eff_test";
        String fileName = "/Users/isihwa/workspace/zerobase/강의자료/코테_답안/0630/테스트케이스/problem"
                + problemNum
                + acOrEc
                + "/" + tcNum + "_o.txt";


        FileReader fileReader = new FileReader(fileName);
        int a;
        while ((a = fileReader.read()) != -1) {
            answer *= 10;
            answer += a - '0';
        }

        return answer;

    }

    public static String[][] testCaseReader(int problemNum, int accOrEff, int tcNum) throws IOException {
        String[][] result = new String[2][];

        String[] a = new String[]{};
        String[] b = new String[]{};


        String acOrEc = accOrEff == 0 ? "/acc_test" : "/eff_test";
        String fileName = "/Users/isihwa/workspace/zerobase/강의자료/코테_답안/0630/테스트케이스/problem"
                + problemNum
                + acOrEc
                + "/" + tcNum + "_i.txt";

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }

        StringBuilder sb1 = new StringBuilder();
        boolean in = false;
        int k = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '[') {
                sb1 = new StringBuilder();
                in = true;
            } else if (sb.charAt(i) == ']') {
                in = false;
                result[k++] = sb1.toString().split(" ");
            } else if (sb.charAt(i) == ',') {

            } else if (in) {
                sb1.append(sb.charAt(i));
            }
        }


        return result;
    }

    public static void main(String[] args) throws IOException {
        String[] a = new String[]{"우유", "시리얼", "우유", "우유"};
        String[] b = new String[]{"우유", "물", "위스키", "막걸리", "소주", "김치냉장고", "시리얼"};

        System.out.println(solution(a, b));


        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= 5; j++) {
                String[][] tc = testCaseReader(3, i, j);

                System.out.println(j + " 번" + "풀이 : " + solution(tc[0], tc[1]));
                System.out.println(j + " 번" + "답안 : " + answerReader(3, i, j) + "\n");

            }

        }


    }


}
