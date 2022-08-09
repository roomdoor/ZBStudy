package ch04.Algorithm_16.A16_1;// 다익스트라 우선순위 큐 사용


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main2 {
    private static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void dijkstra(int v, int[][] data, int start) {
        List<List<Node>> graph = new ArrayList<>();
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] datum : data) {
            graph.get(datum[0]).add(new Node(datum[1], datum[2]));
        }

        int[] distance = new int[v + 1];

        for (int i = 1; i < v + 1; i++) {
            distance[i] = max;
        }

        distance[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>((x, y) -> x.weight - y.weight);
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            List<Node> ways = graph.get(cur.to);

            for (Node next : ways) {
                if (distance[next.to] > distance[cur.to] + next.weight) {
                    distance[next.to] = distance[cur.to] + next.weight;
                    queue.add(new Node(next.to, distance[next.to]));
                }
            }
        }

        System.out.println(Arrays.toString(distance));


    }

    public static void main(String[] args) {
        // Test code
        int[][] data = {{1, 2, 2}, {1, 3, 3}, {2, 3, 4}, {2, 4, 5}, {3, 4, 6}, {5, 1, 1}};
        dijkstra(5, data, 1);
    }
}
