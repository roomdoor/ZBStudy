package ch04.Algorithm_18;
// 프림 알고리즘


import java.util.ArrayList;
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

    public static int prim(int[][] data, int v, int e) {
        int weightSum = 0;
        boolean[] isVisited = new boolean[v + 1];

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] way : data) {
            graph.get(way[0]).add(new Node(way[1], way[2]));
            graph.get(way[1]).add(new Node(way[0], way[2]));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((x, y) -> x.weight - y.weight);
        queue.add(new Node(1, 0));
        int count = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (!isVisited[cur.to]) {
                isVisited[cur.to] = true;
                count++;
                weightSum += cur.weight;
            }
            if (count == v) {
                return weightSum;
            }

            List<Node> ways = graph.get(cur.to);

            for (Node next : ways) {
                if (!isVisited[next.to]) {
                    queue.add(next);
                }
            }
        }

        return weightSum;
    }

    public static void main(String[] args) {
        // Test code
        int v = 7;
        int e = 10;
        int[][] graph = {{1, 3, 1}, {1, 2, 9}, {1, 6, 8}, {2, 4, 13}, {2, 5, 2}, {2, 6, 7}, {3, 4, 12}, {4, 7, 17}, {5, 6, 5}, {5, 7, 20}};

        System.out.println(prim(graph, v, e));
    }
}
