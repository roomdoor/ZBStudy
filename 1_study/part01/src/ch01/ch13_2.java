package ch01;

import java.io.*;
import java.util.Scanner;

public class ch13_2 {
    public static void main(String[] args) throws IOException {
        String inputFile = "./memo.txt";
        String outputFile = "./memo_edit.txt";

        FileWriter fww = new FileWriter("./memo.txt");
        String s = "이시화 파일 만들고 글쓰기  처음 해본다 우와 신기하다\n";
        fww.write(s);
        fww.close();

        PrintWriter pw = new PrintWriter(new FileWriter("./memo.txt", true));
        s = "이시화 이어쓰기 해봐야지";
        pw.println(s);

        s = "이시화 이렇게 이어쓰면 계속 쓸 수 있군 좋군";
        pw.close();

        // 찾을 단어 / 변경 단어 입력 받기

        System.out.print("찾을 단어를 입력하시오 : ");
        Scanner sc = new Scanner(System.in);
        String find = sc.nextLine();
        System.out.print("어떤 단어로 바꿀래요? : ");
        String change = sc.nextLine();

        // 파일 읽기, 변경 및 저장
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        FileWriter fw = new FileWriter(outputFile);

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
            fw.write(line.replace(find, change) + "\n");
        }

        fw.close();
        br.close();


        System.out.println("=================================");
        br = new BufferedReader(new FileReader(outputFile));

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }

        br.close();

    }
}
