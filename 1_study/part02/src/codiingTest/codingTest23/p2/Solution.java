package codiingTest.codingTest23.p2;


import java.util.Arrays;

public class Solution {

	public static int solution(String s, int M, int N) {
		int answer = 0;

		String[] sWords = s.split(" ");
		int len = sWords.length;
		int[] wordsCount = new int[len];

		for (int i = 0; i < len; i++) {
			wordsCount[i] = sWords[i].length();
		}

		int curX = 0;
		int curY = 1;
		int curWord = 0;
		while (true) {
			if (curWord >= len) {
				curWord = 0;
				answer++;
			}

			if (curX == 0) {
				curX = wordsCount[curWord];
				curWord++;
			} else if (curX + 1 + wordsCount[curWord] > N) {
				curY++;

				if (curY > M) {
					break;
				}

				curX = wordsCount[curWord];
				curWord++;
			} else if (curX + 1 + wordsCount[curWord] <= N) {
				curX += 1 + wordsCount[curWord];
				curWord++;
			}

		}

		return answer;
	}

	public static void main(String[] args) {
		String s = "i just called to say i love you by stevie wonder";
		System.out.println(solution(s, 10, 20));

	}
}