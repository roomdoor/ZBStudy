package ch04.Algorithm_09;// Practice
// 정수 num 이 주어졌을 때,
// num 숫자에서 두 자리를 최대 한번만 교체하여 얻을 수 있는 최대값을 출력하세요.

// 입출력 예시
// num: 2736
// 출력: 7236

// 입력: 7116
// 출력: 7611

import java.util.ArrayList;
import java.util.List;

public class Practice5 {
    public static int solution(int num) {
        int temp = num;
        List<Integer> nums = new ArrayList<>();
        while (temp > 0) {
            nums.add(temp % 10);
            temp /= 10;
        }
        List<Integer> maxN = new ArrayList<>(nums);

        for (int i = 1; i < nums.size(); i++) {
            for (int j = i; j < nums.size(); j++) {
                maxN.set(j, Math.max(maxN.get(i - 1), maxN.get(j)));
            }
        }


        return 0;
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(solution(2736));
        System.out.println(solution(7116));
        System.out.println(solution(91));
    }
}
