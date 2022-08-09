package src;

import java.util.HashMap;
import java.util.Map;

public class Practice1 {
    public static void solution(String s) {
//        char[] str = s.toCharArray();
//        char before = 'a';
//
//        int result = 0;
//        for (char c : str) {
//            switch (c) {
//
//                case 'M':
//                    result += 1000;
//                    if (before == 'C') {
//                        result -= 200;
//                    }
//                    break;
//
//                case 'D':
//                    result += 500;
//                    if (before == 'C') {
//                        result -= 200;
//                    }
//                    break;
//
//                case 'C':
//                    // D M
//                    result += 100;
//                    if (before == 'X') {
//                        result -= 20;
//                    }
//                    break;
//                case 'L':
//                    result += 50;
//                    if (before == 'X') {
//                        result -= 20;
//                    }
//                    break;
//
//                case 'X':
//                    // L C
//                    result += 10;
//                    if (before == 'I') {
//                        result -= 2;
//                    }
//                    break;
//                case 'V':
//                    result += 5;
//                    if (before == 'I') {
//                        result -= 2;
//                    }
//                    break;
//
//                case 'I':
//                    result += 1;
//                    break;
//            }
//            before = c;
//        }
//        System.out.println(result);

        int result = 0;
        Map<Character, Integer> roma = new HashMap<>();
        roma.put('I', 1);
        roma.put('V', 5);
        roma.put('X', 10);
        roma.put('L', 50);
        roma.put('C', 100);
        roma.put('D', 500);
        roma.put('M', 1000);

        char[] str = s.toCharArray();

        for (int i = 0; i < str.length - 1; i++) {
            if (roma.get(str[i]) < roma.get(str[i + 1])) {
                result -= roma.get(str[i]);
            } else {
                result += roma.get(str[i]);
            }
        }
        result += roma.get(str[str.length - 1]);

        System.out.println(result);
    }

    public static void main(String[] args) {
        // Test code
        solution("III");
        solution("IV");
        solution("VI");
        solution("XIII");
        solution("XXVI");
        solution("MCMXCIV");
    }
}
