package codiingTest.codingTest15.p3;


import java.util.PriorityQueue;


public class Solution {
    public static int solution(int[] arr, int k) {
        int len = arr.length;

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        queue.add(new int[]{0, arr[0]});

        for (int i = 1; i < len; i++) {
            while (!queue.isEmpty() && queue.peek()[0] < i - k) {
                queue.poll();
            }
            queue.add(new int[]{i, queue.peek()[1] + arr[i]});
        }

        while (queue.peek()[0] != len - 1) {
            queue.poll();
        }

        return queue.peek()[1];
    }
}
