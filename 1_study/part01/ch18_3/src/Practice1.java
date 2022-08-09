import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Practice1 {
    public static void solution(int[] nums) {

        // distinct 중복 거르는 함수
        System.out.println("정답 부분");

        Arrays.stream(nums).distinct().forEach(m -> System.out.print(m + " "));

        System.out.println();

        // set 배열 중복 된놈만 나오는 방법 (set.add 에서 중복된 값 넣으면 false 나오는거 이용한것)
        System.out.println("set 이용한 중복값만 취한것 ");

        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).filter(set::add)
                .forEach(m -> System.out.print(m + " "));

        System.out.println();
        Arrays.stream(nums).filter(n -> !set.add(n))
                .collect(HashSet::new, HashSet::add, HashSet::addAll)   //.collect(Collectors.toSet()) 이거랑 같은거임 근데 왜 안되지??
                .forEach(m -> System.out.print(m + " "));               // nums 배열이 프리미티브 타입이라 그렇다!!! 렙퍼드 타입으로 변경하면 가능

        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        // Test code
        solution(new int[]{1, 1, 2});
        solution(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
    }
}
