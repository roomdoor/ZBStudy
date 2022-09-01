package codiingTest.codingTest15.p1;


public class Solution {
    public boolean solution(String s) {
        int smallE = 0;
        int bigE = 0;
        int spatialS = 0;
        int num = 0;
        char beforeS = '~';
        int countBeforeS = 0;
        boolean upDown = false;// up true down false
        int continuousCount = 0;


        if (!(s.length() >= 6 && s.length() <= 20)) {
            return false;
        }

//        !=33 @=64 #=35 $=36 %= 37 ^=94 &=38 *=43 (=40 )=41
//        숫자 48~57
//        대문자 65~90
//        소문자 97~122

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (a >= 48 && a <= 57) {
                num++;
            } else if (a >= 65 && a <= 90) {
                bigE++;
            } else if (a >= 97 && a <= 122) {
                smallE++;
            } else {
                spatialS++;
            }

            if (a == beforeS) {
                beforeS++;
            } else {
                beforeS = 0;
            }

            if ((a + 1) == beforeS ) {
                if (upDown) {
                    continuousCount++;
                }
                upDown = true;
            }

            if ((a - 1) == beforeS ) {
                if (!upDown) {
                    continuousCount++;
                }
                upDown = false;
            }
            if (beforeS >= 3 || continuousCount >= 2) {
                return false;
            }
        }

        if (bigE == 0 || smallE == 0 || spatialS == 0 || num == 0) {
            return false;
        }
        return true;
    }
}