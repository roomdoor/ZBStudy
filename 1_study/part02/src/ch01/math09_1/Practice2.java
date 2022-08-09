package ch01.math09_1;

import java.util.Arrays;

public class Practice2 {
    public static void solution(int[] arr) {
//        for (int i = arr.length - 1; i >= 1; i--) {       // 더 효율 적인 방법 찾음
//            for (int j = i - 1; j >= 0; j--) {
//                if (arr[i] < arr[j]) {
//                    System.out.println(Arrays.toString(swap(arr, i, j)));
//                    return;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));

        if (arr == null || arr.length < 2) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        int idx = -1;
        for (int i = arr.length - 1; i > 0; i--) {             // 훨씬 적은 방법으로 찾기 가능
            if (arr[i] < arr[i - 1]) {
                idx = i - 1;
                break;
            }
        }

        if (idx == -1) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = arr.length - 1; i > idx; i--) {
            if (arr[i] < arr[idx] && arr[i] != arr[i - 1]) {
                int temp = arr[idx];
                arr[idx] = arr[i];
                arr[i] = temp;
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
    }

    public static int[] swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return arr;
    }

    public static void main(String[] args) {
        // Test code
        int[] arr = {3, 2, 1};
        solution(arr);

        arr = new int[]{1, 9, 4, 7, 6};
        solution(arr);

        arr = new int[]{1, 1, 2, 3};
        solution(arr);
    }
}
