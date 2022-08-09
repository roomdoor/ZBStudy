package ch01.math09_2;

public class Practice1 {

    public static int solution(int n) {
        int result = 0;
        if (n == 0 || n == 1) {
            return 1;
        }

        for (int i = 0; i < n; i++) {
            result += solution(i) * solution(n - 1 - i);
        }


        return result;
    }

    public static void main(String[] args) {
        // Test code
//        System.out.println(solution(0));
//        System.out.println(solution(2));
        System.out.println(solution(5));
        System.out.println(solution(7));
    }
}
