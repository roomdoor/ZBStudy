package codiingTest.codingTest12.p4;

import java.util.*;

public class Solution {
    public static PriorityQueue<Integer> queueMax;
    public static PriorityQueue<Integer> queueMin;

    public static int[] solution(int[] arr, int k) {
        List<Integer> answer = new ArrayList<>();
        queueMax = new PriorityQueue<>(Collections.reverseOrder());
        queueMin = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            queueMin.add(arr[i]);
        }
        for (int i = 0; i < k / 2; i++) {
            queueMax.add(queueMin.poll());
        }
        answer.add(queueMin.peek());

        for (int i = k; i < arr.length; i++) {
            answer.add(culMid(arr[i], arr[i - k], answer.get(answer.size() - 1)));
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int culMid(int num, int before, int mid) {
        if (mid < num) {
            queueMin.add(num);
        } else {
            queueMax.add(num);
        }

        if (queueMax.size() > queueMin.size()) {
            if (!queueMax.remove(before)) {
                queueMin.remove(before);
            }
        } else {
            if (!queueMin.remove(before)) {
                queueMax.remove(before);
            }
        }

        while (Math.abs(queueMax.size() - queueMin.size()) != 1) {
            if (queueMax.size() > queueMin.size()) {
                queueMin.add(queueMax.poll());
            } else {
                queueMax.add(queueMin.poll());
            }
        }

        return queueMin.size() > queueMax.size() ? queueMin.peek() : queueMax.peek();
    }
}

