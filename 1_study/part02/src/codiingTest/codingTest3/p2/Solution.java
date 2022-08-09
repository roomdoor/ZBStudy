package codiingTest.codingTest3.p2;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private static class Num implements Comparable<Num> {
        int cur;
        int count;

        public Num(int cur, int count) {
            this.cur = cur;
            this.count = count;
        }

        @Override
        public int compareTo(Num o) {
            if (this.count == o.count) {
                return o.cur - this.cur;
            } else {
                return this.count - o.count;
            }
        }
    }

    public static int solution(int[] numbers, int target) {
        int[] numCount = new int[target + 1];
        Arrays.fill(numCount, 100);

        Set<Integer> set = Arrays.stream(numbers).boxed().collect(Collectors.toSet());
        if (set.contains(target)) {
            return 1;
        }

        for (int n : set) {
            numCount[n] = 1;
        }

        return bfs(set, numCount, target);
    }

    public static int bfs(Set<Integer> set, int[] numCount, int target) {
        PriorityQueue<Num> queue = new PriorityQueue<>();
        for (int n : set) {
            queue.add(new Num(n, 1));
        }

        while (!queue.isEmpty()) {
            Num cur = queue.poll();
            int num = cur.cur;
            int count = cur.count;

            Set<Integer> newSet = new HashSet<>();
            for (int n : set) {
                newSet = new HashSet<>();
                int mul = num * n;
                int plu = num + n;

                if (mul == target || plu == target && numCount[n] != 100) {
                    return count + numCount[n];
                }

                if (isPossible(mul, target, numCount, count)) {
                    numCount[mul] = count + numCount[n];
                    queue.add(new Num(mul, count + numCount[n]));
                    newSet.add(mul);
                }

                if (isPossible(plu, target, numCount, count)) {
                    numCount[plu] = count + numCount[n];
                    queue.add(new Num(plu, count + numCount[n]));
                    newSet.add(plu);
                }
            }
            set.addAll(newSet);
        }

        return -1;
    }

    public static boolean isPossible(int num, int target, int[] numCount, int count) {
        return num >= 0 && num < target && numCount[num] > count;
    }

    public static void main(String[] args) {
        System.out.println("정답 = " + solution(new int[]{0}
                , 0));          // 1

        System.out.println("정답 = " + solution(new int[]{67, 75, 90, 72, 77, 34, 88, 20, 73, 16, 19, 81, 80, 89, 59, 13, 50, 62, 83, 91, 6, 54, 3, 84, 17, 45, 38, 24, 2, 23, 95, 35, 14, 30, 1, 98, 58, 48, 79, 5, 93, 64, 76, 51, 26, 18, 71, 86, 22, 44, 40, 53, 74, 15, 87, 39, 96, 56, 9, 27, 55, 52, 31, 42, 61, 82, 47, 69, 94, 12, 21, 99, 33, 10, 57, 43, 92, 63, 37, 29, 78, 28, 65, 85, 7, 60, 46, 70, 25}
                , 3298));       //3

        System.out.println("정답 = " + solution(new int[]{2, 53, 21, 35, 38, 65, 66, 43, 31, 93, 16, 22, 52, 3, 37, 78, 30, 90, 84, 97, 69, 63, 1, 98, 76, 13, 32, 41, 68, 15, 55, 27, 82, 33, 91, 79, 12, 42, 36, 25, 86, 60, 45, 85, 96, 8, 9, 49, 44, 40, 20, 11, 18, 58, 71, 95, 26, 23, 88}
                , 1812));       //3

        System.out.println("정답 = " + solution(new int[]{75}
                , 1531));       //-1

        System.out.println("정답 = " + solution(new int[]{0, 0, 10, 1}
                , 610382));     //16
    }
}
