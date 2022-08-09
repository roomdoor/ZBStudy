package ch02.LinearDS11;// Practice1
// 데이터 재정렬
// D_0 -> D_1 -> ... -> D_n-1 -> D_n 순으로 되어 있는 데이터를
// D_0 -> D_n -> D_1 -> D_n-1 -> ... 순이 되도록 재정렬 하시오.

// 입력 예시)
// 입력 데이터: 1 -> 2 -> 3 -> 4 -> 5
// 출력 데이터: 1 -> 5 -> 2 -> 4 -> 3



import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Practice1 {
    public static void reorderData(int[] arr) {
        Deque<Integer> trans = Arrays.stream(arr).boxed().collect(Collectors.toCollection(ArrayDeque::new));
        boolean evne = false;

        while (trans.size() > 1) {
            if (evne) {
                System.out.print(trans.pollLast() + " -> ");
                evne = false;
            } else {
                System.out.print(trans.poll() + " -> ");
                evne = true;
            }
        }
        System.out.println(trans.poll());
    }

    public static void main(String[] args) {
        // Test code
        int[] arr = {1, 2, 3, 4, 5};
        reorderData(arr);   // 1 -> 5 -> 2 -> 4 -> 3

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        reorderData(arr2);  // 1 -> 7 -> 2 -> 6 -> 3 -> 5 -> 4
    }
}
