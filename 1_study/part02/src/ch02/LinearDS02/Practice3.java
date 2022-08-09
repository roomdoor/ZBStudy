package ch02.LinearDS02;// Practice3
// 배열 arr 의 데이터 순서를 거꾸로 변경하세요.
// 추가 배열을 사용하지 않고 구현

// 입출력 예시)
// arr: 1, 3, 5, 7, 9
// 결과: 9, 7, 5, 3, 1


import java.util.Arrays;

public class Practice3 {
    private static void reverse(int[] arr) {
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 9};
        reverse(arr);
    }
}
