package codiingTest.codingTest13.p3;

public class Solution {
    public static final int mod = 100007;           // 오타 방지를 위한 정적변수
    // 여러곳에 자주 쓰이는 복잡한 순서는 static final 로 만들어 오타 방지

    public static int solution(int n, int m) {
        int answer = 0;
        for (int i = 2; i <= n; i++) {      // m 이 가질 수 있는 최소값 2 부터 n 까지 돌면서 m 의 약수인지 확인
            if (m % i == 0) {               // getCount 메소드로 m 이 i 일 때 값을 mod 로 나눈 나머지값을 answer 에 더해줌
                answer += getCount(n, i) % mod;
            }
        }
        return answer % mod;
    }

    private static int getCount(int n, int m) { // nPr 값을 구해주는 메소드
        int answer = 1;                         // n 개 중 호스트를 뺀 n - 1 개 중에 m - 1 개를 골라 순서를 배치하는 경우의 수
        for (int i = n - 1; i > n - m; i--) {
            answer *= i;
            answer %= mod;
        }
        return answer;
    }

    public static void main(String[] args) {
        int a = 24;
        int b = 22;
        System.out.println(solution(a, b));
        System.out.println(55646);

        int result = 1;

        for (int j = a - 1; j > a - b; j--) {
            result *= j;
            result %= mod;
        }
        System.out.println(result);
    }
}
