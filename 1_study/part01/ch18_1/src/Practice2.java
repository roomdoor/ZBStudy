import java.util.Scanner;

public class Practice2 {
    public static void solution() {
        System.out.print("대소문자를 변경할 알파벳을 입력하세요 : ");
        Scanner sc = new Scanner(System.in);
        int ASCII = (int) sc.nextLine().charAt(0);
        int step = (int) 'a' - (int) 'A';

        if (ASCII >= 'a' && ASCII <= 'z') {
            ASCII -= step;
            System.out.println("대문자 변환 : " + (char)ASCII);
        } else if (ASCII >= 'A' && ASCII <= 'Z') {
            ASCII += step;
            System.out.println("소문자 변환 : " + (char) ASCII);
        } else {
            System.out.println("알파벳이 아닙니다.");
        }
    }

    public static void reference() {
        int a = (int) 'a';
        System.out.println("a = " + a);
        int z = (int) 'z';
        System.out.println("z = " + z);
        int A = (int) 'A';
        System.out.println("A = " + A);
        int Z = (int) 'Z';
        System.out.println("Z = " + Z);
        int etc = (int) '%';
        System.out.println("etc = " + etc);
    }

    public static void main(String[] args) {
        reference();    // 아스키 코드 참고
        solution();
    }
}
