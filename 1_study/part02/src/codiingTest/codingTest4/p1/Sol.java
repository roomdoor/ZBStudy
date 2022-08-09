package codiingTest.codingTest4.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sol {
    public String[][] solutionStartWith(String[] titles, String[] lyrics, String[] problems) {
        String[][] answer = new String[problems.length][];          // 정답을 출력할 String[][] 배열
        ArrayList<String> line;                                     // 정답을 받을 배열(정답의 갯수를 알 수 없어서 Array 보단 List로 구현)

        for (int i = 0; i < problems.length; i++) {                 // problems 배열을 돌면서 각 가사에 비교하는 for 문
            line = new ArrayList<>();                               // 정답 담을 배열 초기화
            for (int j = 0; j < lyrics.length; j++) {               // lyrics 배열에 하나하나 비교하며 추가
                if (lyrics[j].startsWith(problems[i])) {            // String 메소드인 startsWith 를 사용하여 problems 배열의 인자 하나를 각 노래 가사와 비교
                    line.add(titles[j]);                            // 일치한다면 정답 배열에 추가
                }
            }
            String[] ArrayLine = line.toArray(new String[0]);       // 정답을 담은 임의 배열인 line 을 answer 에 추가
            answer[i] = ArrayLine;
        }

        return answer;
    }

    //Trie 로 구현한 solutionTrie 메소드는 정답에서 가지고 온 코드라 주석을 달지 않았습니다.
    public String[][] solutionTrie(String[] titles, String[] lyrics, String[] problems) {
        Trie trie = new Trie();
        for (int i = 0; i < titles.length; i++) {
            trie.addLyric(titles[i], lyrics[i]);
        }

        List<String[]> resultList = new ArrayList<>();
        for (String query : problems) {
            resultList.add(trie.getResult(query));
        }
        return resultList.toArray(new String[0][0]);
    }
}

class Node {
    char val;
    Map<Character, Node> children = new HashMap<>();
    List<String> songs = new ArrayList<>();

    public Node(char val) {
        this.val = val;
    }
}

class Trie {
    Node head = new Node('\n');

    public void addLyric(String songName, String lyric) {
        Node curr = head;

        for (char c : lyric.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Node(c));
            }
            curr = curr.children.get(c);
            curr.songs.add(songName);
        }
    }

    public String[] getResult(String query) {
        Node curr = head;

        for (char c : query.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return new String[]{};
            }
            curr = curr.children.get(c);
        }
        return curr.songs.toArray(new String[0]);
    }

    // test case 를 복사해서 String 값에 넣으니 너무 긴 문자열이라는 Exception 이 발생해서 만든 메소드
    // .txt 에서 읽어온 값을 String tc 에 넣고 arr 배열에 '[', ']', ',' 값들을 없애고 배치하는 메소드
    public static void makeTestCase(String tc, String[][] arr) {
        int caseCount = 0;
        boolean in = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tc.length(); i++) {
            if (tc.charAt(i) == '[') {
                sb = new StringBuilder();
                in = false;
            } else if (tc.charAt(i) == ']') {
                in = true;
                arr[caseCount] = sb.toString().split(" ");
                caseCount++;
            } else if (!(tc.charAt(i) == ',' && in || tc.charAt(i) == '"' || tc.charAt(i) == ',')) {
                sb.append(tc.charAt(i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Sol sol = new Sol();

        String[] titles = {};
        String[] lyrics = {};
        String[] problems = {};

        String testCaseNumber = "4";
        String testType = false ? "acc" : "eff";

        // test case 5_i.txt 파일을 읽어서 String t에 넣는 부분
        BufferedReader br = new BufferedReader(new FileReader("/Users/isihwa/workspace/zerobase/강의자료/0616/tc/problem1/" + testType + "_test/" + testCaseNumber + "_i.txt"));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        String t = sb.toString();

        // test case 5_o.txt 파일을 읽어서 String r에 넣는 부분
        br = new BufferedReader(new FileReader("/Users/isihwa/workspace/zerobase/강의자료/0616/tc/problem1/" + testType + "_test/" + testCaseNumber + "_o.txt"));
        sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        String r = sb.toString();
        r = r.substring(1, r.length() - 1);

        // testCaseArray = {titles, lyrics, problems}
        String[][] testCaseArray = new String[][]{titles, lyrics, problems};
        makeTestCase(t, testCaseArray); // String t (5_i.txt) 를 int[][] testCaseArray 배열에 title, lyrics, problems 배열에 넣어주는 메소드

        String[][] answerArray = new String[testCaseArray[2].length][];
        makeTestCase(r, answerArray); // String r (5_o.txt) 를 int[][] answerArray 배열에 넣어주는 메소드

        long bt = System.currentTimeMillis();
        String[][] cul = sol.solutionStartWith(testCaseArray[0], testCaseArray[1], testCaseArray[2]); // testCaseArray = {titles, lyrics, problems}
        long at = System.currentTimeMillis();
        System.out.println("startWith 메소드를 활용한 solution 메소드 걸린 시간 = " + (at - bt));
        System.out.println("테스트 케이스를 넣고 solution 메소드를 통과한 값이 answerArray 의 값과 같은가?? ");
        if (Arrays.deepEquals(answerArray, cul)) {
            System.out.println("OK");
        } else {
            System.out.println("NO");
        }
        System.out.println();


        bt = System.currentTimeMillis();
        cul = sol.solutionTrie(testCaseArray[0], testCaseArray[1], testCaseArray[2]); // testCaseArray = {titles, lyrics, problems}
        at = System.currentTimeMillis();
        System.out.println("Trie 알고리즘을 활용한 solution 메소드 걸린 시간 = " + (at - bt));

        System.out.println("테스트 케이스를 넣고 solution 메소드를 통과한 값이 answerArray 의 값과 같은가?? ");
        if (Arrays.deepEquals(answerArray, cul)) {
            System.out.println("OK");
        } else {
            System.out.println("NO");
        }

    }
}