package codiingTest.codingTest14.p2;

public class Solution {
    private static class Num {
        int num;
        int index;

        public Num(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    public static String solution(String s, int k) {
        Num[] arr = new Num[s.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Num(s.charAt(i) - '0', i);
        }

        return "";
    }
}