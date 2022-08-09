package ch01.codeTestTest.p2;

import java.util.ArrayList;

public class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        ArrayList<Integer> result = new ArrayList<>();
        int before = -1;

        for (int num : arr) {
            if (num != before) {
                result.add(num);
                before = num;
            }
        }
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}
