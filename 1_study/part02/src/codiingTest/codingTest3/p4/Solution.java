package codiingTest.codingTest3.p4;

import java.util.PriorityQueue;

public class Solution {
    private static class Node implements Comparable<Node> {
        int x1;
        int y1;

        int x2;
        int y2;
        int count;

        int dis;

        public Node(int x1, int y1, int x2, int y2, int count) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.count = count;
            this.dis = (int) (Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
            ;
        }

        @Override
        public int compareTo(Node o) {
            if (this.dis - o.dis == 0) {
                return this.count - o.count;
            } else {
                return this.dis - o.dis;
            }
        }

    }

    public static int solution(int x1, int y1, int x2, int y2) {
        int answer = 0;
        Node first = new Node(x1, y1, x2, y2, 0);
        answer = bfs(first);

        return answer;
    }

    public static int bfs(Node first) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(first);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.dis == 1 || cur.dis == 5) {
                return cur.count + 1;
            }


            if (cur.x1 - cur.x2 > 0) {
                if (cur.y1 - cur.y2 > 0) {
                    queue.add(new Node(cur.x1 - 1, cur.y1, cur.x2 + 1, cur.y2 + 1, cur.count + 1));
                    queue.add(new Node(cur.x1, cur.y1 - 1, cur.x2 + 1, cur.y2 + 1, cur.count + 1));
                } else if (cur.y1 - cur.y2 == 0) {
                    queue.add(new Node(cur.x1 - 1, cur.y1, cur.x2 + 1, cur.y2 + 1, cur.count + 1));
                    queue.add(new Node(cur.x1 - 1, cur.y1, cur.x2 + 1, cur.y2 - 1, cur.count + 1));
                } else {
                    queue.add(new Node(cur.x1 - 1, cur.y1, cur.x2 + 1, cur.y2 - 1, cur.count + 1));
                    queue.add(new Node(cur.x1, cur.y1 + 1, cur.x2 + 1, cur.y2 - 1, cur.count + 1));
                }
            } else if (cur.x1 - cur.x2 == 0) {
                if (cur.y1 - cur.y2 > 0) {
                    queue.add(new Node(cur.x1, cur.y1 - 1, cur.x2 + 1, cur.y2 + 1, cur.count + 1));
                    queue.add(new Node(cur.x1, cur.y1 - 1, cur.x2 - 1, cur.y2 + 1, cur.count + 1));
                } else {
                    queue.add(new Node(cur.x1, cur.y1 + 1, cur.x2 + 1, cur.y2 - 1, cur.count + 1));
                    queue.add(new Node(cur.x1, cur.y1 + 1, cur.x2 - 1, cur.y2 - 1, cur.count + 1));
                }

            } else {
                if (cur.y1 - cur.y2 > 0) {
                    queue.add(new Node(cur.x1 + 1, cur.y1, cur.x2 - 1, cur.y2 + 1, cur.count + 1));
                    queue.add(new Node(cur.x1, cur.y1 - 1, cur.x2 - 1, cur.y2 + 1, cur.count + 1));
                } else if (cur.y1 - cur.y2 == 0) {
                    queue.add(new Node(cur.x1 + 1, cur.y1, cur.x2 - 1, cur.y2 + 1, cur.count + 1));
                    queue.add(new Node(cur.x1 + 1, cur.y1, cur.x2 - 1, cur.y2 - 1, cur.count + 1));
                } else {
                    queue.add(new Node(cur.x1 + 1, cur.y1, cur.x2 - 1, cur.y2 - 1, cur.count + 1));
                    queue.add(new Node(cur.x1, cur.y1 + 1, cur.x2 - 1, cur.y2 - 1, cur.count + 1));
                }
            }


        }

        return -1;
    }

    //    Node next = new Node(cur.x1, cur.y1, cur.x2, cur.y2, cur.count + 1)
    public static void main(String[] args) {
        System.out.println(solution(2, 4, 5, -3));
    }
}
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 4; j++) {
//                    int nx1 = cur.x1 + dx1[i];
//                    int ny1 = cur.y1 + dy1[i];
//                    int nx2 = cur.x2 + dx2[j];
//                    int ny2 = cur.y2 + dy2[j];
//                    if (isPossible(nx1, ny1, nx2, ny2)) {
//                        queue.add(new Node(nx1, ny1, nx2, ny2, cur.count + 1));
//                    }
//                }
//            }



