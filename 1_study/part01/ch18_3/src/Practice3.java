import java.util.*;
import java.util.stream.Collectors;

public class Practice3 {
    public static void solution(int[] arr, int k, int x) {

        Set<Integer> result = new HashSet<>();
        for (int i = 0; result.size() < k ; i++) {
            if (k > arr.length && result.size() == arr.length) break;
            int minus = x - i;
            int plus = x + i;
            result.addAll(Arrays.stream(arr).filter(n -> n == minus).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
            if (result.size() >= k) break;
            result.addAll(Arrays.stream(arr).filter(n -> n == plus).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

        }

        System.out.println(result);

//        Map<Integer, List<Integer>> map = new HashMap<>();
//
//        for (int value : arr) {
//            int diff = Math.abs(x - value);
//
//            List<Integer> cur = map.get(diff);
//            if (cur == null) {
//                map.put(diff, new ArrayList<>(Collections.singletonList(value)));
//            } else {
//                int idx = cur.size();
//                for (int j = 0; j < cur.size(); j++) {
//                    if (cur.get(j) > value) {
//                        idx = j;
//                        break;
//                    }
//                }
//                cur.add(idx, value);
//            }
//        }
//
//        List<Integer> result = new ArrayList<>();
//        int cnt = 0;
//        while (map.size() > 0) {
//            int minDiff = map.keySet().stream().min((a, b) -> a - b).get();
//            List<Integer> cur = map.get(minDiff);
//            map.remove(minDiff);
//
//            while (cur.size() > 0) {
//                result.add(cur.get(0));
//                cur.remove(0);
//                cnt++;
//
//                if (cnt == k) {
//                    break;
//                }
//            }
//        }
//
//        Collections.sort(result);
//
//        System.out.println(result);
    }

    public static void main(String[] args) {
        // Test code
        int[] arr = {1, 2, 3, 4, 5};
        solution(arr, 4, 3);

        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        solution(arr, 5, 5);

        arr = new int[]{2, 4};
        solution(arr, 1, 3);

        arr = new int[]{2, 4};
        solution(arr, 3, 3);
    }
}
