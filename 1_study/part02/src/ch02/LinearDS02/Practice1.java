package ch02.LinearDS02;// Practice1
// 배열 arr 의 모든 데이터에 대해서,
// 짝수 데이터들의 평균과 홀수 데이터들의 평균을 출력하세요.

// 입출력 예시)
// 배열 arr: 1, 2, 3, 4, 5, 6, 7, 8, 9
// 결과:
// 짝수 평균: 5.0
// 홀수 평균: 5.0

public class Practice1 {
    private static void practice01(int[] arr) {

        double oddSum = 0;
        int oddNum = 0;
        double evenSum = 0;
        int evenNum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evenSum += arr[i];
                evenNum++;
            } else {
                oddSum += arr[i];
                oddNum++;
            }
        }
        double oddAverage;
        double evenAverage;

        if (oddNum == 0) {
            oddAverage = 0;
        } else {
            oddAverage = oddSum / oddNum;
        }

        if (evenNum == 0) {
            evenAverage = 0;
        } else {
            evenAverage = evenSum / evenNum;
        }



        System.out.println("짝수 평균 : " + evenAverage);
        System.out.println("홀수 평균 : " + oddAverage);
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        practice01(arr);

    }

}
