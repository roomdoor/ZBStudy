package ch02.LinearDS02;// Practice4
// 배열 arr 에서 peek 값 모두 출력

// 입출력 예시)
// arr: 3, 1, 2, 6, 2, 2, 5, 1, 9, 10, 1, 11
// 결과: 3, 6, 5, 10, 11

public class Practice4 {
    public static void main(String[] args) {

        int[] arr = new int[]{3, 1, 2, 6, 2, 2, 5, 1, 9, 10, 1, 11};
        int beforeMax = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > beforeMax) {
                System.out.print(arr[i] + " ");
                beforeMax = arr[i];
            }
        }

    }

}
