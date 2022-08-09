package ch03.NonLinearDS10;// Practice4
// 문자열 s 가 주어졌을 때, 문자열 내에 동일한 알파벳이 연속적으로 배치되지 않도록 재배치 하세요.
// 재배치가 가능한 경우 재정렬한 문자열을 반환하고 불가능한 경우 null 을 반환하세요.

// 입출력 예시
// 입력: "aabb"
// 출력: "abab"

// 입력: "aaca"
// 출력: null

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Practice4 {
    public static String solution(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String string : s.split("")) {
            map.put(string, map.getOrDefault(string, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(
                (x, y) -> y.getValue() - x.getValue());

        queue.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();
        String pre = "";
        while (!queue.isEmpty()) {
            Map.Entry<String, Integer> next = queue.poll();

            if (pre.equals(next.getKey())) {
                Map.Entry<String, Integer> next2 = queue.poll();
                if (!queue.isEmpty()) {
                    queue.add(next);
                } else {
                    return null;
                }
                next = next2;
            }

            if (next.getValue() > 1) {
                next.setValue(next.getValue() - 1);
                queue.add(next);
            }

            sb.append(next.getKey());
            pre = next.getKey();
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(solution("aabb"));
        System.out.println(solution("aaaaabccd"));
        System.out.println(solution("aaca"));
    }
}
