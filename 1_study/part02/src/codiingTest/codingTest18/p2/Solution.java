package codiingTest.codingTest18.p2;


public class Solution {

	public static int solution(int n) {
		int answer = 0;

		int len = n * 5;
		boolean[] list = new boolean[len + 1];
		list[0] = false;
		list[1] = true;

		for (int i = 2; i <= 5; i++) {
			if (!list[i]) {
				list[i] = true;
				for (int j = i * i; j <= len; j += i) {
					list[j] = true;
				}
			}
		}

		for (int i = 7; (i * i) <= len; i++) {
			if (!list[i]) {
				for (int j = i * i; j <= len; j += i) {
					list[j] = false;
				}
			}
		}

		int count = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i]) {
				count++;
				System.out.println(count + "번째 " + i);
				if (count == n) {
					answer = i;
				}
			}

		}

		return answer;
	}

	public static void main(String[] args) {
//		System.out.println(solution(5));
		System.out.println(solution(10));


	}
}