package codiingTest.codingTest7.p1;


public class Solution {
    public static String solution(int[][] img) {
        String answer = "";
        if (img == null || img.length == 0) {
            return "";
        }

        if (img.length == 1) {
            return String.valueOf(img[0][0]);
        }

        answer = compress(img.length, 0, 0, img);

        return answer;
    }


    public static String compress(int n, int x, int y, int[][] img) {

        StringBuilder sb = new StringBuilder();
        if (!isSameChecked(n, x, y, img)) {
            return String.valueOf(img[x][y]);

        }
        sb.append("(");

        if (isSameChecked(n / 2, x, y, img)) {
            sb.append(compress(n / 2, x, y, img));
        } else {
            sb.append(img[x][y]);
        }

        if (isSameChecked(n / 2, x, y + n / 2, img)) {
            sb.append(compress(n / 2, x, y + n / 2, img));
        } else {
            sb.append(img[x][y + n / 2]);
        }

        if (isSameChecked(n / 2, x + n / 2, y, img)) {
            sb.append(compress(n / 2, x + n / 2, y, img));
        } else {
            sb.append(img[x + n / 2][y]);
        }

        if (isSameChecked(n / 2, x + n / 2, y + n / 2, img)) {
            sb.append(compress(n / 2, x + n / 2, y + n / 2, img));
        } else {
            sb.append(img[x + n / 2][y + n / 2]);
        }
        sb.append(")");


        return sb.toString();
    }

    public static boolean isSameChecked(int n, int x, int y, int[][] img) {
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (img[x][y] != img[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{0, 0, 0, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
//        System.out.println(solution(a));
//
//        a = new int[][]{{0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1}};
//        System.out.println(solution(a));
//
//        a = new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 1}};
//        System.out.println(solution(a));
//
//        a = new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
//        System.out.println(solution(a));

        a = new int[][]{{1, 0}, {0, 1}};
        System.out.println(solution(a));

    }
}