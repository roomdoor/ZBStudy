package ch04.Algorithm_19;// Practice1
// 2250년, 인류는 지구 뿐 아니라 여러 행성을 다니며 살고 있다.
// 이 행성 간을 빨리 오가기 위해 새롭게 터널을 구축하려 한다.

// 행성은 (x, y, z) 좌표로 주어진다.
// 행성1: (x1, y1, z1), 행성2: (x2, y2, z2)
// 이 때 행성간 터널 연결 비용은 min(|x1-x2|, |y1-y2|, |z1-z2|) 로 계산한다.

// n 개의 행성 사이를 n-1 개의 터널로 연결하는데 드는 최소 비용을 구하는 프로그램을 작성하세요.

// 입출력 예시
// 입력:
// data = {{11, -15, -15}, {14, -5, -15}, {-1, -1, -5}, {10, -4, -1}, {19, -4, 19}}
// 출력: 4


import java.util.Arrays;

public class Practice1 {
    private static int[] parents;

    private static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a > b) {
                parents[a] = b;
            } else {
                parents[b] = a;
            }
        }
    }

    public static int solution(int[][] data) {
        int len = data.length;
        int[][] ways = new int[len * len][3];
        parents = new int[len];
        for (int i = 0; i < len; i++) {
            parents[i] = i;
        }

        int k = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int dis = Math.min(Math.abs(data[i][0] - data[j][0]), Math.min(Math.abs(data[i][1] - data[j][1]), Math.abs(data[i][2] - data[j][2])));
                ways[k++] = new int[]{i, j, dis};
            }
        }

        Arrays.sort(ways, (x, y) -> x[2] - y[2]);

        int result = 0;
        for (int[] way : ways) {
            if (find(way[0]) != find(way[1])) {
                union(way[0], way[1]);
                result += way[2];
            }
        }


        return result;
    }

    public static void main(String[] args) {
        // Test code
        int[][] data = {{11, -15, -15}, {14, -5, -15}, {-1, -1, -5}, {10, -4, -1}, {19, -4, 19}};
        System.out.println(solution(data));
    }
}
