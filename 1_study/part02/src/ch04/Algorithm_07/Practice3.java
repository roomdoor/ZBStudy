package ch04.Algorithm_07;// Practice
// 문자열 s 를 거꾸로 출력하는 프로그램을 작성하세요.
// 단, 각 단어의 알파벳 순서는 그대로 출력합니다.
// 문장에 공백이 여러개일 시, 단어와 단어 사이 하나의 공백을 제외한 나머지 공백은 제거하세요.

// 입출력 예시
// 문자열 s: "the sky is blue"
// 출력: "blue is sky the"

// 문자열 s: "  hello      java    "
// 출력: "java hello"


public class Practice3 {
    public static String solution(String s) {
        s = removeSpaces(s);

        String[] ss = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = ss.length - 1; i >= 0; i--) {
            result.append(ss[i]).append(" ");
        }
        result.delete(result.length() - 1, result.length());


        return result.toString();
    }

    public static String removeSpaces(String s) {
        int p1 = 0;
        int p2 = 0;
        String[] ss = s.split("");

        while (ss[p1].equals(" ") && ss[p2].equals(" ")) {
            if (p1 == p2) {
                p2++;
            }
            ss[p2++] = "";
        }
        if (ss[p1].equals(" ")) {
            ss[p1] = "";
        }
        p1 = p2;

        while (p1 < ss.length && p2 < ss.length) {
            while (ss[p1].equals(" ") && ss[p2].equals(" ")) {
                if (p1 == p2) {
                    p2++;
                }
                ss[p2++] = "";
                if (p2 == ss.length) {
                    ss[p1] = "";
                }
            }
            p1 = p2++;
        }

        StringBuilder result = new StringBuilder();
        for (String c : ss) {
            result.append(c);
        }
        return result.toString();
    }

    public static void reverseString(char[] cArr, int i, int j) {

    }

    public static void reverseWords(char[] cArr, int length) {
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(solution("the sky is blue"));
        System.out.println(solution("  hello      java    "));

    }
}
