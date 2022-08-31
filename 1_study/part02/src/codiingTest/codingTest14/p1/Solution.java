package codiingTest.codingTest14.p1;

/*
 * 직선을 그렸을 때 어떤 정사각형을 지나는지 파악하는 수학문제입니다.
 * 기본적으로는 직선이 아래나 우측으로 이동하기 때문에, N+M개의 정사각형을 지납니다.
 * 하지만, 정확히 모서리를 지나는 경우가 있기 때문에 숫자가 몇 번 덜 증가하죠!
 * 그 횟수는 총 N과 M의 gcd(최대공약수)가 됩니다.
 */

public class Solution {
    public int solution(int N, int M) {
        // 일반적으로 직선이 지나는 정사각형의 개수 = N + M
        // 정확히 모서리가 맞아서 직선을 지나지 않는 경우의 수 = N과 M의 최대공약수
        return N + M - gcd(N, M);
    }

    // 유클리드 호제법을 이용한 최대공약수
    int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}