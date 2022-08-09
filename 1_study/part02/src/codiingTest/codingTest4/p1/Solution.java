package codiingTest.codingTest4.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// 강의에서 배운 trie 를 사용 해야 할 것 같았는데 String 의 starsWith 라는 메소드를 활용 하는게 더 간단하게 풀 수 있을 것 같아서 starsWith 사용
// 시간복잡도
// Trie : 생성 복잡도 + 탐색 복잡도 = O(MN) + O(MN)
// statsWith : 탐색 복잡도 = O(N^4)????
public class Solution {
    public String[][] solution(String[] titles, String[] lyrics, String[] problems) {
        String[][] answer = new String[problems.length][];          // 정답을 출력할 String[][] 배열
        ArrayList<String> line;                                     // 정답을 받을 배열(정답의 갯수를 알 수 없어서 Array 보단 List로 구현)

        for (int i = 0; i < problems.length; i++) {                 // problems 배열을 돌면서 각 가사에 비교하는 for 문
            line = new ArrayList<>();                               // 정답 담을 배열 초기화
            for (int j = 0; j < lyrics.length; j++) {               // lyrics 배열에 하나하나 비교하며 추가
                if (lyrics[j].startsWith(problems[i])) {            // String 메소드인 startsWith 를 사용하여 problems 배열의 인자 하나를 각 노래 가사와 비교
                    line.add(titles[j]);                            // 일치한다면 정답 배열에 추가
                }
            }
            String[] ArrayLine = line.toArray(new String[0]); // 정답을 담은 임의 배열인 line 을 answer 에 추가
            answer[i] = ArrayLine;
        }

        return answer;
    }

    public static void testCase(String tc, String[][] cc) {
        int caseCount = 0;
        boolean in = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tc.length(); i++) {
            if (tc.charAt(i) == '[') {
                sb = new StringBuilder();
                in = false;
            } else if (tc.charAt(i) == ']') {
                in = true;
                cc[caseCount] = sb.toString().split(" ");
                caseCount++;
            } else if (!(tc.charAt(i) == ',' && in || tc.charAt(i) == '"' || tc.charAt(i) == ',')) {
                sb.append(tc.charAt(i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        String[] a = {};
        String[] b = {};
        String[] c = {};

        BufferedReader br = new BufferedReader(new FileReader("/Users/isihwa/workspace/zerobase/강의자료/0616/tc/problem1/eff_test/5_i.txt"));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        String t = sb.toString();

        br = new BufferedReader(new FileReader("/Users/isihwa/workspace/zerobase/강의자료/0616/tc/problem1/eff_test/5_o.txt"));
        sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        String r = sb.toString();

        String[][] cc = new String[][]{a, b, c};
        testCase(t, cc);

        String[][] rr = new String[cc[2].length][];
        testCase(r, rr);

        long bt = System.currentTimeMillis();
        String[][] cul = sol.solution(cc[0], cc[1], cc[2]);
        long at = System.currentTimeMillis();
        System.out.println("startWith 를 활용한 solution 메소드 걸린 시간 = " + (at - bt));

        if (Arrays.deepEquals(rr, cul)) {
            System.out.println("OK");
        } else {
            System.out.println("NO");
        }
    }

}
