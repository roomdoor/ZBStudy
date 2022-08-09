package src;

import java.util.Arrays;

public class Practice5 {
    public static int solution(int[] ratings) {
        int sum = 0;
        int[] candyNum = new int[ratings.length];
        candyNum[0] = 1;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candyNum[i] = candyNum[i - 1] + 1;
            } else {
                candyNum[i] = candyNum[i - 1] - 1;
                if (candyNum[i] == 0) {
                    candyNum[i] = 1;
                    for (int j = i - 1; j >= 0; j--) {
                        if (candyNum[j] == candyNum[j + 1]) {
                            candyNum[j] += 1;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        for (int a : candyNum) {
            sum += a;
        }
        return sum;
    }

    public static void main(String[] args) {
        // Test code
        int[] ratings = {1, 2, 3};
        System.out.println(solution(ratings));

        ratings = new int[]{3, 2, 1};
        System.out.println(solution(ratings));

        ratings = new int[]{1, 0, 2};
        System.out.println(solution(ratings));

        ratings = new int[]{1, 2, 2};
        System.out.println(solution(ratings));

        ratings = new int[]{1, 3, 5, 3, 1, 3, 5, 7, 5, 3, 1, 0};
        System.out.println(solution(ratings));
    }
}
