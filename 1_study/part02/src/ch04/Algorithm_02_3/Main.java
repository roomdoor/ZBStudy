package ch04.Algorithm_02_3;

// 알고리즘 - 정렬_3
// 기수 정렬

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static void radixSort(int[] arr) {
        Queue<Integer>[] radixTable = new Queue[10];

        for (int i = 0; i < 10; i++) {
            radixTable[i] = new ArrayDeque<>();
        }

        int idx = 0;
        int div = 1;
        int maxLen = getMaxLen(arr);

        for (int i = 0; i < maxLen; i++) {
            for (int k : arr) {
                radixTable[(k / div) % 10].add(k);
            }

            for (int j = 0; j < 10; j++) {
                while (!radixTable[j].isEmpty()) {
                    arr[idx++] = radixTable[j].poll();
                }
            }
            idx = 0;
            div *= 10;
        }
    }

    public static int getMaxLen(int[] arr) {
        int maxLen = 0;
        for (int j : arr) {
            int len = (int) Math.log10(j) + 1;
            if (maxLen < len) {
                maxLen = len;
            }
        }

        return maxLen;
    }


    public static void main(String[] args) {
        // Test code
        int[] arr = {10, 32, 52, 27, 48, 17, 99, 56};
        radixSort(arr);
        System.out.println("기수 정렬: " + Arrays.toString(arr));
    }
}
