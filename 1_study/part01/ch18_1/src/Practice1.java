import java.util.ArrayList;
import java.util.List;

public class Practice1 {
    public static void solution(int num) {
        int isMinus = 1;
        if (num < 0) {
            isMinus = -1;
            num *= -1;
        }

        List<Integer> arr = new ArrayList<>();

        while (num > 0) {
            arr.add(num % 10);
            num /= 10;
        }
        int result = 0;
        for (Integer integer : arr) {
            result *= 10;
            result += integer;
        }
        System.out.println(result * isMinus);


    }

    public static void main(String[] args) {
        // Test code
        solution(12345);
        solution(-12345);
        solution(100);
        solution(0);

    }
}
