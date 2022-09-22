package codiingTest.codingTest17.p4;

import java.util.PriorityQueue;

public class Sol {

	public static int sol(int dest, int start, int[] station, int[] fuel) {
		int len = station.length;
		int curPosition = 0;
		int curFuel = start;
		int count = 0;

		PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);

		for (int i = 0; i <= len; i++) {
			int curDest;

			if (i == len) {
				curDest = dest;
			} else {
				curDest = station[i];
			}

			curFuel -= (curDest - curPosition);

			while (curFuel < 0 && !queue.isEmpty()) {
				curFuel += queue.poll();
				count++;
			}

			if (curFuel < 0) {
				return -1;
			}

			if (i < len) {
				queue.add(fuel[i]);
				curPosition = station[i];
			}
		}

		return count;
	}

	public static void main(String[] args) {
		int dest = 12;
		int start = 6;
		int[] station = {2, 3, 5, 10};
		int[] fuel = {5, 2, 3, 1};
		System.out.println(sol(dest, start, station, fuel));
	}

}
