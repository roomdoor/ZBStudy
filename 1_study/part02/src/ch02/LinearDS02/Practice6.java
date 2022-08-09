package ch02.LinearDS02;// Practice6
// 배열 arr 에서 중복 값을 제거한 새 배열을 만드시오.

// 입출력 예시)
// arr: 1, 5, 3, 2, 2, 3, 1, 4, 1, 2, 3, 5
// 결과: 1, 5, 3, 2, 4

import java.util.Arrays;

public class Practice6 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 2, 2, 3, 1, 4, 1, 2, 3, 5};
        int[] newArr = new int[arr.length];
        int count = 0;
        boolean isSame = false;
        for (int i = 0; i < arr.length; i++) {
            isSame = false;
            for (int j = 0; j <= i; j++) {
                if (newArr[j] == arr[i]) {
                    isSame = true;
                    break;
                }
            }

            if (!isSame) {
                newArr[count++] = arr[i];
            }
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(newArr));
        for (int i = 0; i < count; i++) {
            System.out.print(newArr[i] + " ");
        }
    }
}
