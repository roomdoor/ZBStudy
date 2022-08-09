package ch04.Algorithm_02_1;

import java.util.Arrays;

public class Main {
    // 오름차순 기준 정렬 알고리즘

    // 버블 정렬
    public static void bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
//
//        for (int i = arr.length - 1; i >= 0; i--) {
//            for (int j = 0; j < i; j++) {
//
//            }
//        }
    }

    // 삽입 정렬
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    // 선택 정렬
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    public static void main(String[] args) {
        // Test code
        int[] arr = {3, 5, 2, 7, 1, 4};

        long bt = System.currentTimeMillis();
        bubbleSort(arr);
        long at = System.currentTimeMillis();
        System.out.println("버블 정렬: " + Arrays.toString(arr));
        System.out.println("걸린 시간 " + (at - bt));

        arr = new int[]{3, 5, 2, 7, 1, 4};
        bt = System.currentTimeMillis();
        insertionSort(arr);
        at = System.currentTimeMillis();
        System.out.println("삽입 정렬: " + Arrays.toString(arr));
        System.out.println("걸린 시간 " + (at - bt));

        arr = new int[]{3, 5, 2, 7, 1, 4};
        bt = System.currentTimeMillis();
        selectionSort(arr);
        at = System.currentTimeMillis();
        System.out.println("선택 정렬: " + Arrays.toString(arr));
        System.out.println("걸린 시간 " + (at - bt));

    }
}
