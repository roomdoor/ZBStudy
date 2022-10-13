package codiingTest.fileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CodingTestFileReader {

	public static void fileReader(int pNum, int accOrEff, int[][] a) throws IOException {
		String aOrE = accOrEff == 0 ? "acc" : "eff";
		String address = "/Users/isihwa/workspace/zerobase/강의자료/코테_답안/0714" +
			"/테스트케이스/problem2/" + aOrE + "_test/" + pNum + "_i.txt";

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(address));
		String line;

		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
	}
}
