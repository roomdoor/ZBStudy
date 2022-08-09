package ch04.Algorithm_10;

public class Main {

    public static int getMax(int[] arr, int left, int right) {
        int mid = (left + right) / 2;

        if (right == left) {
            return arr[left];
        }

        int leftMax = getMax(arr, left, mid);
        int rightMax = getMax(arr, mid + 1, right);

        return Math.max(leftMax,rightMax);
    }


    public static void main(String[] args) {
        int arr[] = {6, 2, 9, 8, 1, 4, 17, 5};
        System.out.println(getMax(arr, 0, arr.length - 1));
    }
}
