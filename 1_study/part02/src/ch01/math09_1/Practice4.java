package ch01.math09_1;

import java.util.HashSet;
import java.util.Set;

public class Practice4 {
    public static boolean solution(int n) {
        Set<Integer> set = new HashSet<>();
        while (set.add(n)) {
            int temp = 0;

            while (n > 0) {
                int oneNum = n % 10;
                temp += Math.pow(oneNum, 2);
                n /= 10;
            }
            n = temp;

            if (n == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(solution(19));
        System.out.println(solution(2));
        System.out.println(solution(61));
    }
}
