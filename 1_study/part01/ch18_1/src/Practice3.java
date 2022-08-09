import java.util.Arrays;

public class Practice3 {
    public static String solution(char[] str, char[] find, char[] to) {

        StringBuilder s = new StringBuilder();
        for (char ss : str) {
            s.append(ss);
        }
        StringBuilder f = new StringBuilder();
        for (char ff : find) {
            f.append(ff);
        }
        StringBuilder t = new StringBuilder();
        for (char tt : to) {
            t.append(tt);
        }

        return s.toString().replaceAll(f.toString(), t.toString());
    }

    public static void main(String[] args) {
        // Test code
        String str = "Hello Java, Nice to meet you! Java is fun!";
        String find = "Java";
        String to = "자바";

        // 기존 String replace
        System.out.println(str.replace(find, to));

        // 자체 구현 replace
        char[] strExArr = str.toCharArray();
        char[] findArr = find.toCharArray();
        char[] toArr = to.toCharArray();
        System.out.println(solution(strExArr, findArr, toArr));

        strExArr = "POP".toCharArray();
        findArr = "P".toCharArray();
        toArr = "W".toCharArray();
        System.out.println(solution(strExArr, findArr, toArr));
    }
}
