package ch03.NonLinearDS02;
// Practice2
// 각각의 에지에 가중치가 있는 포화 이진 트리가 있다.
// 루트에서 각 리프까지의 경로 길이를 모두 같게 설정하고,
// 이 때, 모든 가중치들의 총합이 최소가 되도록 하는 프로그램을 작성하세요.


public class Practice2 {
    public static int result = 0;

    public static void solution(int h, int[] w) {

        result = 0;
        int[] arr = new int[(int) Math.pow(2, h + 1) - 1];
        System.out.println(dfs(0, 0, h, w, arr));
        System.out.println(result);
    }

    public static int dfs(int idx, int dp, int h, int[] w, int[] arr) {
        if (h == dp) {
            result += w[idx - 1];
            arr[idx] = w[idx - 1];
            return w[idx - 1];
        }

        int left = dfs(idx * 2 + 1, dp + 1, h, w, arr);
        int right = dfs(idx * 2 + 2, dp + 1, h, w, arr);

        if (idx > 0) {
            arr[idx] = w[idx - 1] + Math.max(left, right);
            result += w[idx - 1];
        } else {
            arr[idx] = Math.max(left, right);
        }
        result += Math.abs(left - right);

        return arr[idx];
    }

    public static void main(String[] args) {
        // Test code
        int h = 2;
        int[] w = {2, 2, 2, 1, 1, 3};
        solution(h, w);
        System.out.println();

        h = 3;
        w = new int[]{1, 2, 1, 3, 2, 4, 1, 1, 1, 1, 1, 1, 1, 1};
        solution(h, w);
    }
}
