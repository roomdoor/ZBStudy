
public class Practice4 {
    public static void solution(int n, int type) {
        switch (type) {

            case 1:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;

            case 2:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < i + 1; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;

            case 3:
                for (int i = 0; i < n; i++) {
                    StringBuilder s = new StringBuilder();
                    for (int j = 0; j < i + 1; j++) {
                        s.append("*");
                    }
                    System.out.printf("%" + n + "s\n", s.toString());
                }
                break;

            case 4:
                for (int i = 0; i < n; i++) {
                    StringBuilder s = new StringBuilder();
                    for (int j = 0; j < i * 2 + 1; j++) {
                        s.append("*");
                    }
                    int b = n + i;
                    System.out.printf("%" + b + "s\n", s.toString());
                }
                break;

            case 5:
                for (int i = 0; i < n; i++) {
                    StringBuilder s = new StringBuilder();
                    if (i < (n + 1) / 2) {
                        for (int j = 0; j < i * 2 + 1; j++) {
                            s.append("*");
                        }
                        int b = n / 2 + i + 1;
                        System.out.printf("%" + b + "s\n", s.toString());
                    } else {
                        for (int j = 0; j < (n - i - 1) * 2 + 1; j++) {
                            s.append("*");
                        }
                        int b = n / 2 + (n - i);
                        System.out.printf("%" + b + "s\n", s.toString());
                    }

                }
                break;

        }
        System.out.println();

    }

    public static void type1(int n) {
        System.out.println("== Type1 ==");

        System.out.println();
    }

    public static void type2(int n) {
        System.out.println("== Type2 ==");

        System.out.println();
    }

    public static void type3(int n) {
        System.out.println("== Type3 ==");

        System.out.println();
    }

    public static void type4(int n) {
        System.out.println("== Type4 ==");

        System.out.println();
    }

    public static void type5(int n) {
        System.out.println("== Type5 ==");

        System.out.println();
    }

    public static void main(String[] args) {
        // Test code
        solution(3, 1);
        solution(3, 2);
        solution(3, 3);
        solution(3, 4);
        solution(7, 5);
    }
}
