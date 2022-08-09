package codiingTest.codingTest7.p5;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Solution {
    public static int solution(int[] nums) {
        int answer = 0;

        int len = nums.length;
        PartArr[][] partArr = new PartArr[len][len];
        for (int i = 0; i < len; i++) {
            partArr[i][i] = new PartArr(i, i, nums[i], nums[i], nums[i] * nums[i]);
            answer = Math.max(answer, partArr[i][i].result);
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                partArr[i][j] = PartArr.addEnd(partArr[i][j - 1], j, nums);
                answer = Math.max(answer, partArr[i][j].result);
            }
        }

        return answer;
    }

    public static class PartArr {
        int start;
        int end;
        int min;
        int sum;
        int result;

        @Override
        public String toString() {
            return "PartArr{" +
                    "start=" + start +
                    ", end=" + end +
                    ", min=" + min +
                    ", sum=" + sum +
                    ", result=" + result +
                    '}';
        }

        public static PartArr addEnd(PartArr partArr, int add, int[] nums) {
            int newMin = Math.min(partArr.min, nums[add]);
            int newSum = partArr.sum + nums[add];
            int newResult = newSum * newMin;

            return new PartArr(partArr.start, partArr.end, newMin, newSum, newResult);
        }

        public PartArr() {
        }

        public PartArr(int start, int end, int min, int sum, int result) {
            this.start = start;
            this.end = end;
            this.min = min;
            this.sum = sum;
            this.result = result;
        }
    }

    public static void main(String[] args) throws IOException {
        SolutionS t = new SolutionS();
        int testcaseNum = 5;
        BufferedReader br = new BufferedReader(
                new FileReader(
                        "/Users/isihwa/workspace/zerobase/강의자료/코테_답안/0707/테스트케이스/problem5/eff_test/"
                                + testcaseNum + "_i.txt"));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);

        int[] a = Arrays.stream(sb.toString().split(", ")).mapToInt(Integer::parseInt).toArray();

        long start = System.nanoTime();
        System.out.println(t.solutionS(a));
        long end = System.nanoTime();
        long time1 = end - start;
        System.out.println("답안 코드 걸리 시간 : " + time1);


        start = System.nanoTime();
        System.out.println(solution(a));
        end = System.nanoTime();
        long time2 = end - start;
        System.out.println("나의 코드 걸린 시간 : " + time2);
        System.out.println((time2 - time1) / 1000);

    }
}

class SolutionS {
    int[] nums, cumSum;

    public int solutionS(int[] nums) {
        int N = nums.length;
        this.nums = nums;
        cumSum = new int[N];
        cumSum[0] = nums[0];
        for (int i = 1; i < N; i++) {
            cumSum[i] = cumSum[i - 1] + nums[i];
        }

        return solve(0, N - 1);
    }

    public int S(int l, int r) {
        if (l == 0) {
            return cumSum[r];
        } else {
            return cumSum[r] - cumSum[l - 1];
        }
    }

    public int solve(int left, int right) {
        if (left == right) {
            return nums[left] * nums[left];
        }

        int mid = left + (right - left) / 2;
        int ret = Math.max(solve(left, mid),
                solve(mid + 1, right));

        int l = mid;
        int r = mid + 1;
        int mn = Math.min(nums[l], nums[r]);
        ret = Math.max(ret, (nums[l] + nums[r]) * mn);

        while (left < l || r < right) {
            if ((r < right) && (left == l || nums[l - 1] < nums[r + 1])) {
                r++;
                mn = Math.min(mn, nums[r]);
            } else {
                l--;
                mn = Math.min(mn, nums[l]);
            }
            ret = Math.max(ret, S(l, r) * mn);
        }
        return ret;
    }
}
