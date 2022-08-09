package ch04.Algorithm_16.A16_3;// 알고리즘 - 최단 경로 알고리즘
// 플로이드-워셜

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void floydWarshall(int v, int e, int[][] data, int start) {
        int INF = Integer.MAX_VALUE;
        int[][] dis = new int[v + 1][v + 1];
        for (int i = 0; i < v + 1; i++) {
            for (int j = 0; j < v + 1; j++) {
                if (i == j) {
                    dis[i][j] = 0;
                } else {
                    dis[i][j] = INF;
                }
            }
        }

        for (int[] datum : data) {
            dis[datum[0]][datum[1]] = datum[2];
        }

        for (int k = 0; k < v + 1; k++) {
            for (int i = 0; i < v + 1; i++) {
                for (int j = 0; j < v + 1; j++) {
                    if (dis[i][k] != INF && dis[k][j] != INF) {
                        dis[i][j] = Math.min(dis[i][k] + dis[k][j], dis[i][j]);
                    }
                }
            }
        }

        for (int i = 1; i < v + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                if (dis[i][j] == INF) {
                    System.out.printf("%5s ", "INF");
                } else {
                    System.out.printf("%5d ", dis[i][j]);
                }
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        // Test code
        int[][] data = {{1, 2, 8}, {1, 3, 6}, {1, 5, 5}, {2, 3, -5}, {2, 4, 1}, {2, 6, 4}, {3, 4, 4}, {4, 7, 3}, {5, 6, 5}, {6, 2, 0}, {6, 7, -7}};
        floydWarshall(7, 11, data, 1);
        System.out.println();

        data = new int[][]{{1, 2, 8}, {1, 3, 6}, {1, 5, 5}, {2, 3, -5}, {2, 4, 1}, {2, 6, 4}, {3, 4, 4}, {4, 7, 3}, {5, 6, 5}, {6, 2, -5}, {6, 7, -7}};
        floydWarshall(7, 11, data, 1);
    }
}
