
public class Practice5 {
    public static int solution(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                int diff = leftMax - height[left];
                if (diff > 0) {
                    result += diff;
                }
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                int diff = rightMax - height[right];
                if (diff > 0) {
                    result += diff;
                }
            }
        }


        return result;
    }

    public static void main(String[] args) {
        // Test code
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution(height));

        height = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(solution(height));
    }
}
