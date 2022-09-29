package codiingTest.codingTest19.p5;


import java.util.Arrays;

public class Solution {

	public static int[] solution(String[] strings) {
		int[] capacities = new int[4];
		boolean isOther = true;
		String[][] extensions = new String[][]{
			{"mp3", "aac", "flac"},
			{"jpg", "bmp", "gif"},
			{"mp4", "avi", "mkv"}
		};

		for (String s : strings) {
			re:
			for (int i = 0; i < 3; i++) {
				isOther = true;
				for (int j = 0; j < 3; j++) {
					if (s.contains(extensions[i][j])) {
						capacities[i] += Integer.parseInt(
							s.split(" ")[1]
								.replace("b", ""));
						isOther = false;
						break re;
					}
				}
			}

			if (isOther) {
				capacities[3] += Integer.parseInt(
					s.split(" ")[1]
						.replace("b", ""));
			}
		}

		return capacities;
	}

	public static void main(String[] args) {
		String[] a = new String[]{"mv.song.mp3 11b", "greatSong.flac 1000b", "not3.txt 5b",
			"video.mp4 200b", "game.exe 100b", "mov1e.mkv 10000b"};
		System.out.println(Arrays.toString(solution(a)));
	}
}