package src;

import java.util.Locale;

public class Practice4 {
    static final int BACK_SPACE = 8;
    static final int SHIFT = 16;
    static final int CAPS_LOCK = 20;
    static final int SPACE_BAR = 32;
    static final int KEY_LEFT = 37;
    static final int KEY_RIGHT = 39;
    static final int INSERT = 155;
    static final int DELETE = 127;
    static String[] specialKey = {")", "!", "@", "#", "$", "%", "^", "&", "*", "("};

    //        97~122: 알파벳 소문자
    //        48~57: 숫자 0~9
    public static String solution(int[] keyLog) {

        boolean OnCaps = false;
        boolean OnShift = false;
        boolean OnInsert = false;

        StringBuilder result = new StringBuilder();
        int num = 0;
        for (int cmd : keyLog) {
            switch (cmd) {
                case BACK_SPACE:
                    result.deleteCharAt(num - 1);
                    num--;
                    break;
                case SHIFT:
                    OnShift = true;
                    break;
                case CAPS_LOCK:
                    OnCaps = !OnCaps;
                    break;
                case SPACE_BAR:
                    result.insert(num, " ");
                    num++;
                    break;
                case KEY_LEFT:
                    num = Math.max(0, num - 1);
                    break;
                case KEY_RIGHT:
                    num = Math.min(result.length(), num + 1);
                    break;
                case DELETE:
                    result.deleteCharAt(num);
                    break;
                case INSERT:
                    OnInsert = !OnInsert;
                    break;

                default:
                    String s = decodeCmd(cmd, OnShift, OnCaps, OnInsert);
                    insertCmd(OnInsert, result, num, s);

                    if (OnShift) {
                        OnShift = false;
                    }
                    num++;
                    break;
            }
        }

        return result.toString();
    }

    public static void insertCmd(boolean OnInsert, StringBuilder result, int num, String s) {
        if (OnInsert) {
            result.replace(num, num + 1, s);
        } else {
            result.insert(num, s);
        }
    }

    public static String decodeCmd(int cmd, boolean OnShift, boolean OnCaps, boolean OnInsert) {
        String s = "";
        if (cmd >= 97 && cmd <= 122) {
            if (OnCaps != OnShift) {
                s = String.valueOf((char) cmd).toUpperCase(Locale.ROOT);
            } else {
                s = String.valueOf((char) cmd);
            }
        } else if (cmd >= 48 && cmd <= 57) {
            if (OnShift) {
                int m = cmd - 48;
                s = specialKey[m];
            } else {
                s = String.valueOf((char) cmd);
            }
        }

        return s;
    }

    public static void main(String[] args) {
        // Test code
        int[] keyLog = {16, 106, 101, 108, 108, 111, 37, 37, 37, 37, 37, 155, 16, 104};
        System.out.println(solution(keyLog));

        keyLog = new int[]{20, 104, 16, 105, 32, 20, 16, 106, 97, 118, 97};
        System.out.println(solution(keyLog));

        keyLog = new int[]{49, 51, 8, 50, 51, 53, 55, 37, 37, 127, 127, 52, 53};
        System.out.println(solution(keyLog));

        keyLog = new int[]{20, 97, 98, 16, 99, 16, 100, 16, 49, 16, 50, 16, 51};
        System.out.println(solution(keyLog));
    }
}
