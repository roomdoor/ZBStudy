package ch04.Algorithm_19;// Practice2
// V 개의 건물과 E 개의 도로로 구성된 도시가 있다.
// 도로는 양방향이고 연결된 도로는 유지하는데 비용이 든다.

// 새롭게 도시 계획을 개편하며 기존의 도시를 두 개의 도시로 분할해서 관리하고자 한다.
// 도시에는 하나 이상의 건물이 있어야 하고,
// 도시 내의 임의의 두 건물은 도로를 통해 이동 가능해야 한다.
// 두 건물 간 도로가 직접 연결이 되지 않고 다른 건물을 통해서 이동해도 가능하다.
// 새롭게 개편하는 도시 계획에 따라 구성했을 때 최소한의 도로 유지비 비용 계산 프로그램을 작성하세요.

// 입출력 예시
// 입력:
// v: 7
// e: 12
// data: {{1, 2, 3}, {1, 3, 2}, {1, 6, 2}, {2, 5, 2},
//        {3, 2, 1}, {3, 4, 4}, {4, 5, 3}, {5, 1, 5},
//        {6, 4, 1}, {6, 5, 3}, {6, 7, 4}, {7, 3, 6}}
// 출력: 8


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Practice2 {
    private static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void solution(int v, int e, int[][] data) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] way : data) {
            graph.get(way[0]).add(new Node(way[1], way[2]));
            graph.get(way[1]).add(new Node(way[0], way[2]));
        }

        boolean[] isVisited = new boolean[v + 1];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0));
        int max = Integer.MIN_VALUE;
        int result = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (!isVisited[cur.to]) {
                isVisited[cur.to] = true;
                count++;
                max = Math.max(max, cur.weight);
                result += cur.weight;
            } else {
                continue;
            }

            if (count == v) {
                System.out.println(result - max);
                return;
            }

            List<Node> ways = graph.get(cur.to);
            for (Node next : ways) {
                if (!isVisited[next.to]) {
                    queue.add(next);
                }
            }

        }

    }

    public static void main(String[] args) {
        // Test code
        int v = 7;
        int e = 12;
        int[][] data = {{1, 2, 3}, {1, 3, 2}, {1, 6, 2}, {2, 5, 2},
                {3, 2, 1}, {3, 4, 4}, {4, 5, 3}, {5, 1, 5},
                {6, 4, 1}, {6, 5, 3}, {6, 7, 4}, {7, 3, 6}};
        solution(v, e, data);
    }
}
