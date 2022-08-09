package ch04.Algorithm_16.A16_2;// 알고리즘 - 최단 경로 알고리즘
// 벨만-포드

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static class Node {
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void bellmanFord(int v, int e, int[][] data, int start) {
        int INF = Integer.MAX_VALUE;
        boolean isMinus = false;
        Node[] graph = new Node[e];
        for (int i = 0; i < data.length; i++) {
            graph[i] = new Node(data[i][0], data[i][1], data[i][2]);
        }

        int[] distance = new int[v + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        for (int i = 0; i < v + 1; i++) {
            for (Node cur : graph) {
                if (distance[cur.to] > distance[cur.from] + cur.weight) {
                    distance[cur.to] = distance[cur.from] + cur.weight;
                    if (i == v) {
                        isMinus = true;
                    }
                }
            }
        }

        if (isMinus) {
            System.out.println("마이너스 루프");
        } else {
            for (int i = 1; i < distance.length; i++) {
                if (distance[i] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(distance[i] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test code
        int[][] data = {{1, 2, 8}, {1, 3, 6}, {1, 5, 5}, {2, 3, -5}, {2, 4, 1}, {2, 6, 4}, {3, 4, 4}, {4, 7, 3}, {5, 6, 5}, {6, 2, 0}, {6, 7, -7}};
        bellmanFord(7, 11, data, 1);

        data = new int[][]{{1, 2, 8}, {1, 3, 6}, {1, 5, 5}, {2, 3, -5}, {2, 4, 1}, {2, 6, 4}, {3, 4, 4}, {4, 7, 3}, {5, 6, 5}, {6, 2, -5}, {6, 7, -7}};
        bellmanFord(7, 11, data, 1);
    }
}
