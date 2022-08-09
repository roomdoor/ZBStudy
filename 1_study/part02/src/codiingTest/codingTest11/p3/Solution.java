package codiingTest.codingTest11.p3;

import java.util.Arrays;
import java.util.Objects;

// String 메서드를 이용하여 푸는 문제
public class Solution {
    public static boolean solution(String s, String[] words) {
        boolean answer = false;
        if (s.length() == 0 && words.length == 0) {
            return true;
        }
        // words 의 단어로 시작된다면 find 메서드 시작
        for (String word : words) {
            if (s.startsWith(word)) {                               // find 함수 재귀로 탐색
                answer = find(s.substring(word.length()), words);    // words 에 단어로 시작된다면 s 에서 words 단어를 제거하고 재귀함수에 넣어줌
            }
            if (answer) {
                return true;
            }
        }

        return false;
    }

    public static boolean find(String s, String[] words) {   // 재귀 함수
        if (s.length() == 0) {                              // 남아있는 s 문자열이 없다면 true 반환
            return true;
        }

        for (String word : words) {                         // 다시 words 에 단어로 시작하는지 탐색
            if (s.startsWith(word)) {
                String substring = s.substring(word.length());  // 시작한다면 sub String 만들어서 다시 find 함수 실행
                return find(substring, words);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] ss = new String[]{"zer", "ro", "ze", "base"};
//        System.out.println(solution("zerobase", ss));

//        ss = new String[]{};
//        System.out.println(solution("", ss));

        ss = new String[]{"zkmzqqlqk", "bsexoue", "qiywn", "eppdhwdbvv", "eppdhwdbvv", "ouq", "s", "brsspwbp", "zvhb", "qrk", "lvh", "shg", "iilcbxhghm", "robpnc", "wh", "koxpbawsm", "dfjwefaj", "nqqc", "dfjwefaj", "fa", "fhacw", "ylg", "yksibssqsz", "ikmiqjx", "yksibssqsz", "tdujrl", "rxc", "oztthnsb", "ewbq", "akst", "wjjnkrcv", "th", "wnjxqu", "ucqo", "ovsvac", "majmbdg", "nqqc", "axgezhj", "rxc", "fczg", "qdpgye", "anadss", "wrazg", "ouq", "d", "lfaz", "bnhpprol", "yd", "th", "ygqnh", "oubap", "aozxghftdk", "ikmiqjx", "rspbr", "uyiul", "cydul", "q", "rse", "ylg", "rublcriqjp", "egmk", "nwyhoubf", "yarizg", "agozbvymtl", "egmk", "qwtnhcetp", "brjr", "yfdziahpgg", "gaes", "bd", "yksibssqsz", "qi", "cnn", "lvh", "bnhpprol", "fczg", "zyrcxoy", "hzvjmomn", "u", "s", "nrgqebxwli", "nolqfam", "axgezhj", "uumx", "pa", "g", "noptyunkh", "vm", "rnyj", "dtbxcqi", "majmbdg", "lazbvr", "oztthnsb", "dz", "wjk", "klugdxpe", "brsspwbp", "eiwixnugg", "odv", "vm"};
        System.out.println(solution("rnyjeppdhwdbvvdmajmbdglazbvrdzzyrcxoybrjrwrazgakstbrsspwbpoubapdfjwefajbrjraxgezhjqwtnhcetpovsvacvmbrsspwbpouqoubapshgylg", ss));
    }
}
