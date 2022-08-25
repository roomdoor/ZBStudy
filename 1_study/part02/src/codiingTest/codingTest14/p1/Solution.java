package codiingTest.codingTest14.p1;

public class Solution {
    public static int solution(int N, int M) {
        int answer = count(N, M);
        return answer;
    }

    public static int count(double N, double M) {
        if (N <= 1) {
            return (int) Math.ceil(M);
        }

        if (M <= 1) {
            return (int) Math.ceil(N);
        }


        int num = count(N / 2, M / 2);


        return 2 * num;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 3));
    }
}
// 4, 9
// 2, 4.5
// 1, 2.75
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
