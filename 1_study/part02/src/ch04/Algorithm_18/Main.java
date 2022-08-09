package ch04.Algorithm_18;
// 알고리즘 - 최소 신장 트리
// 크루스칼 알고리즘

import java.util.Arrays;

public class Main {

    private static int[] parents;

    public static int kruskal(int[][] data, int v, int e) {
        int weightSum = 0;
        Arrays.sort(data, (x, y) -> x[2] - y[2]);
        parents = new int[v + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int[] way : data) {
            if (find(way[0]) == find(way[1])) {
                continue;
            }

            union(way[0], way[1]);
            weightSum += way[2];

        }


        return weightSum;
    }

    public static int find(int x) {
        if (parents[x] == x) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x > y) {
                parents[x] = y;
            } else {
                parents[y] = x;
            }
        }
    }

    public static void main(String[] args) {
        // Test code
        int v = 7;
        int e = 10;
        int[][] graph = {{1, 3, 1}, {1, 2, 9}, {1, 6, 8}, {2, 4, 13}, {2, 5, 2}, {2, 6, 7}, {3, 4, 12}, {4, 7, 17}, {5, 6, 5}, {5, 7, 20}};

        System.out.println(kruskal(graph, v, e));
    }
}
