package ch01.math09_1;

import java.util.ArrayList;

public class Practice1 {
    public static ArrayList<ArrayList<Integer>> solution(int numRows) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (numRows > 0) {
            for (int i = 0; i < numRows; i++) {
                result.add(new ArrayList<>());

                for (int j = 0; j < i + 1; j++) {
                    int beforeLeft;
                    int beforeRight;

                    if (j == 0 || j == i) {
                        result.get(i).add(1);
                    } else {
                        beforeLeft = result.get(i - 1).get(j - 1);
                        beforeRight = result.get(i - 1).get(j);
                        result.get(i).add(beforeLeft + beforeRight);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(5));
    }
}
