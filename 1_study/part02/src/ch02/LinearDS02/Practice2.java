package ch02.LinearDS02;// Practice2
// 배열 arr 에서 target 에 해당하는 값의 인덱스를 출력
// 해당 값이 여러 개인 경우 가장 큰 인덱스 출력

// 입출력 예시)
// 배열 arr: 1, 1, 100, 1, 1, 1, 100
// 결과: 6

public class Practice2 {
    private static void maxIndex(int[] arr) {
        int max = Integer.MIN_VALUE;
        int MIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= max) {
                max = arr[i];
                MIndex = i;
            }
        }
        if (MIndex >= 0) {
            System.out.println("결과 : " + MIndex);
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 100, 1, 1, 1, 100};
        maxIndex(arr);
    }
}
