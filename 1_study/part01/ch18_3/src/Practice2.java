import java.util.*;

public class Practice2 {
    public static ArrayList<Integer> solution(int[] nums) {

        Set<Integer> set = new HashSet<>();

        return Arrays.stream(nums).filter(n -> !set.add(n))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public static void main(String[] args) {
        // Test code
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(solution(nums));

        nums = new int[]{1, 1, 2};
        System.out.println(solution(nums));

        nums = new int[]{1, 3, 1, 3, 5, 5};
        System.out.println(solution(nums));
    }
}
