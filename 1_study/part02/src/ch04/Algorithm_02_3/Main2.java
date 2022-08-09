package ch04.Algorithm_02_3;

// 계수 정렬

import java.util.*;

public class Main2 {
    public static void countingSort(int[] arr) {
        int maxNum = Arrays.stream(arr).max().getAsInt();
        int[] countArr = new int[maxNum + 1];

        for (int j : arr) {
            countArr[j]++;
        }

        int idx = 0;

        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i] != 0) {
                arr[idx++] = i;
                countArr[i]--;
            }
        }

        //Max 값 하나만 드럽게 클때 할 수 있는 방법
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        idx = 0;
        for (Integer integer : list) {

            int cnt = map.get(integer);
            while (cnt > 0) {
                arr[idx++] = integer;
                cnt--;
            }
        }
    }


    public static void main(String[] args) {
        // Test code
        int[] arr = {10, 32, 10, 27, 32, 17, 99, 56};
        countingSort(arr);
        System.out.println("계수 정렬: " + Arrays.toString(arr));
    }
}

