package codiingTest.codingTest15.p4;

import java.util.Arrays;

public class Solution {
    public int solution(int[] numsDivide, int[] numsDivided) {
        int answer = 0;
        int gcd = gcd(numsDivided[0], numsDivided[1]);
        for (int i = 2; i < numsDivided.length; i++) {
            gcd = gcd(gcd, numsDivided[i]);
            if (gcd == 1) {
                break;
            }
        }
        Arrays.sort(numsDivide);
        for (int i = 0; i < numsDivide.length; i++) {
            if (numsDivide[i] == gcd || gcd % numsDivide[i] == 0) {
                return i;
            }
            if (numsDivide[i] > gcd) {
                return -1;
            }
        }

        return answer;
    }

    int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}