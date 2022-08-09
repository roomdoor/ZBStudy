package codiingTest.codingTest10.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static List<Integer> answer;                     // 정답 배열을 위한 List

    private static class Node implements Comparable<Node> { //
        int parents;
        Node left;
        Node right;
        int depth;
        int rl; // right = 1, left = 0

        public Node(int parents, int depth) {
            this.parents = parents;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node o) {
            if (o.depth == this.depth) {
                return o.rl - this.rl;
            } else {
                return o.depth - this.depth;
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "parents=" + parents +
                    ", depth=" + depth +
                    ", rl=" + rl +
                    '}';
        }
    }


    public static int[] solution(int N, int[][] left, int[][] right) {
        Node[] nodeArr = new Node[N];
        answer = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            nodeArr[i] = new Node(i, 0);
        }

        for (int[] ints : left) {
            nodeArr[ints[0]].left = nodeArr[ints[1]];
            nodeArr[ints[1]].depth = nodeArr[ints[0]].depth + 1;
            nodeArr[ints[1]].rl = 0;

        }

        for (int[] ints : right) {
            nodeArr[ints[0]].right = nodeArr[ints[1]];
            nodeArr[ints[1]].depth = nodeArr[ints[0]].depth + 1;

            nodeArr[ints[1]].rl = 1;
        }

        for (int i = 0; i < N; i++) {
            System.out.println(nodeArr[i]);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.addAll(Arrays.asList(nodeArr).subList(0, N));
        while (!queue.isEmpty()) {
            answer.add(queue.poll().parents);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }


    public static void main(String[] args) {
        int[][] a = new int[][]{{0, 1}, {1, 5}, {2, 3}};
        int[][] b = new int[][]{{0, 2}, {3, 4}};

        System.out.println(Arrays.toString(solution(6, a, b)));
    }
}
