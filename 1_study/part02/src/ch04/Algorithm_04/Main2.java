package ch04.Algorithm_04;// 자바 기본 binarySearch

import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 10, 20, 30, 40, 50, 60};

        System.out.println("데이터 있을 때 ==================");
        System.out.println(Arrays.binarySearch(arr, 1));
        System.out.println(Arrays.binarySearch(arr, 10));
        System.out.println(Arrays.binarySearch(arr, 30));

        System.out.println("데이터 없을 때 ==================");
        System.out.println(Arrays.binarySearch(arr, 3));
        System.out.println(Arrays.binarySearch(arr, 4));
        System.out.println(Arrays.binarySearch(arr, 6));

    }
}
