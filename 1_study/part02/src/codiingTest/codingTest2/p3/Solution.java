package codiingTest.codingTest2.p3;

public class Solution {
    public static int i = 0;

    public static String solution(String code) {
        return culString(code.toCharArray(), "", 1);
    }

    public static String culString(char[] arr, String result, int num) {
        while (i < arr.length) {
            if (arr[i] == '{') {
                i++;
                String temp = culString(arr, "", num);
                for (int j = 0; j < num; j++) {
                    result += temp;
                }
            } else if (arr[i] == '}') {
                i++;
                return result;
            } else if (arr[i] - '0' >= 1 && arr[i] - '0' <= 9) {
                num = arr[i] - '0';
                i++;
            } else {
                result += arr[i];
                i++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("5{he2{l}o}friend"));
    }
}

//hellohellohellohellohellofriend
//"5{he2{l}o}friend"
