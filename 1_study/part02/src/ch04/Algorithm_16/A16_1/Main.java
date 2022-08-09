package ch04.Algorithm_16.A16_1;
// 알고리즘 - 최단 경로 알고리즘
// 다익스트라 기본 구현

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private  static class Node {
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

        boolean[] isVisited = new boolean[v + 1];

        for (int i = 1; i < graph.size(); i++) {
            isVisited[i] = true;
            List<Node> ways = graph.get(i);
            for (Node next : ways) {
                if (!isVisited[next.to] && distance[i] + next.weight < distance[next.to]) {
                    distance[next.to] = distance[i] + next.weight;
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
