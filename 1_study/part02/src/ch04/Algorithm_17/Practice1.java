package ch04.Algorithm_17;
// Practice1
// 2차원 배열 data 에 그래프 데이터가 주어졌다.
// 무방향이고 간선에 가중치 값이 있는 그래프이다.
// 1번 정점에서 N 번 정점으로 최단 경로로 이동하려고 하는데,
// 두 정점을 경유해서 가려고 한다.
// 1번 점점에서 출발하여 두 정점을 경유하여 N 번 정점으로 가는 최단 경로를 구하세요.

// 입출력 예시)
// 입력 data: {{1, 2, 3}, {1, 3, 5}, {1, 4, 4}, {2, 3, 3}, {2, 4, 5}, {3, 4, 1}}
// 출력: 7


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Practice1 {
    public static class Node implements Comparable<Node> {
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

    public static List<List<Node>> graph;
    public static int INF = Integer.MAX_VALUE;

    public static int solution(int[][] data, int v, int via1, int via2, int start, int n) {
        graph = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] datum : data) {
            graph.get(datum[0]).add(new Node(datum[1], datum[2]));
        }

        int startToV1 = dijkstra(v, start, via1);
        int V1ToV2 = dijkstra(v, via1, via2);
        int V2ToEnd = dijkstra(v, via2, n);
        int dis1 = INF;
        if (!(startToV1 == INF || V1ToV2 == INF || V2ToEnd == INF)) {
            dis1 = startToV1 + V1ToV2 + V2ToEnd;
        }

        int starToV2 = dijkstra(v, start, via2);
        int V2ToV1 = dijkstra(v, via2, via1);
        int V1ToEnd = dijkstra(v, via1, n);
        int dis2 = INF;
        if (!(starToV2 == INF || V2ToV1 == INF || V1ToEnd == INF)) {
            dis2 = starToV2 + V2ToV1 + V1ToEnd;
        }

        return Math.min(dis1, dis2);
    }

    public static int dijkstra(int v, int start, int end) {
        int[] dis = new int[v + 1];
        Arrays.fill(dis, INF);
        dis[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            List<Node> ways = graph.get(cur.to);
            for (Node next : ways) {
                if (dis[next.to] > dis[cur.to] + next.weight) {
                    dis[next.to] = dis[cur.to] + next.weight;
                    queue.add(new Node(next.to, dis[next.to]));
                }
            }
        }

        return dis[end];
    }

    public static void main(String[] args) {
        // Test code
        int[][] data = {{1, 2, 3}, {1, 3, 5}, {1, 4, 4}, {2, 3, 3}, {2, 4, 5}, {3, 4, 1}};
        int v = 4;
        int via1 = 2;
        int via2 = 3;
        int start = 1;
        int n = 4;
        System.out.println(solution(data, v, via1, via2, start, n));
    }
}
