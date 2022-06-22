package ch04.Algorithm_03;
// Practice2
// 문자열 배열 strs 가 주어졌을 때 anagram 으로 묶어서 출력하는 프로그램을 작성하세요.
// anagram 은 철자 순서를 바꾸면 같아지는 문자를 의미한다.
// 예) elvis <-> lives
// anagram 그룹 내에서 출력 순서는 상관 없다.

// 입출력 예시
// 입력: "eat", "tea", "tan", "ate", "nat", "bat"
// 출력: [[eat, tea, ate], [bat], [tan, nat]]


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Practice2 {
    public static ArrayList<ArrayList<String>> solution(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] cArr = s.toCharArray();
            System.out.println(Arrays.toString(cArr));
            sort(cArr);
            System.out.println(Arrays.toString(cArr));
            String key = String.valueOf(cArr);
            System.out.println(key);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    public static void sort(char[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if (arr[j] < arr[j - 1]) {
                    char temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test code
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution(strs));

        strs = new String[]{"abc", "bac", "bca", "xyz", "zyx", "aaa"};
        System.out.println(solution(strs));
    }
}
