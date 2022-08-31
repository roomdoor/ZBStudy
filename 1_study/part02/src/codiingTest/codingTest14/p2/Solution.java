package problem2;

/* 그리디 문제! 입니다.
 * 기본적으로 "높은 자릿수, 높은 숫자"를 먼저 제거합니다.
 * 그런데, 첫 자리를 제거했을 때 리딩 제로가 나오는 것을 고려해야 합니다.
 * 이러한 과정을 고려해서 재귀적으로 구현하면 아래 답안 코드가 됩니다.
 */

public class Solution {
    public String solution(String s, int k) {
        // 제거할 자릿수가 실제 자릿수 이상이면 0
        // 리딩제로 덕분에 k > s.length()인 상황이 나올 수 있다.
        if (k >= s.length()) {
            return "0";
        }

        // 제거할 것이 더 없으면, 리딩제로를 제거하고 출력
        if (k == 0) {
            return stripLeadingZeros(s);
        }

        char[] chars = s.toCharArray();
        // 2번째 자리가 0이면, 첫번째 자리를 제거했을 때 리딩제로 발생
        // 그러면 하나를 없애면서 2개 이상을 없애는 효과가 되므로,
        // 다른 숫자가 더 큰 것을 지우는 것 보다 더 우선시 됩니다.
        if (chars[1] == '0') {
            return solution(
                    // 리딩 제로를 다 제거하고 넘깁니다.
                    // 왜 이것이 되느냐? 어차피 리딩 제로는 우리가 제거할
                    // 일이 없는 숫자이기 때문에, k <- k-1로 바꾸면서도
                    // 리딩 제로를 전부 제거하고 넘겨도 괜찮습니다.
                    // 단, 이것때문에 첫번째 조건문에서 k가 s의 길이보다
                    // 길어지는 경우도 같이 커버해 줘야 합니다.
                    stripLeadingZeros(s.substring(1)),
                    k-1);
        } else {
            int ind = 0;
            // 높은 자릿수부터 보면서, 숫자가 감소하면 멈춤
            // 숫자가 같으면 더 높은 자릿수를 우선으로 합니다.
            for (int i = 1; i < chars.length; i++) {
                if (chars[i-1] < chars[i]) {
                    ind = i;
                } else if (chars[i-1] != chars[i]) {
                    break;
                }
            }
            return solution(
                    // 해당 숫자 제거하고 k <- k-1로 재귀적으로 반복
                    s.substring(0, ind) + s.substring(ind+1),
                    k-1);
        }
    }

    String stripLeadingZeros(String s) {
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c != '0') {
                return s.substring(i);
            }
        }
        return "0";
    }
}