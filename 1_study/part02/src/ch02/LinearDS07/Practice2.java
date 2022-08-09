package ch02.LinearDS07;// Practice2
// 괄호 짝 검사

// 입출력 예시)
// 입력: "("
// 출력: Fail

// 입력: ")"
// 출력: Fail

// 입력: "()"
// 출력: Pass

import java.util.Objects;
import java.util.Stack;

public class Practice2 {
    public static void checkParenthesis(String str) {
        Stack stack = new Stack();
        for (String s : str.split("")) {
            if (Objects.equals(s, "(")) {
                stack.push("(");
            } else {
                if (stack.isEmpty()) {
                    System.out.println("FAIL!");
                    return;
                }
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            System.out.println("PASS!");
        } else {
            System.out.println("FAIL!");
        }
    }

    public static void main(String[] args) {
        // Test code
        checkParenthesis("(");          // FAIL!
        checkParenthesis(")");          // FAIL!
        checkParenthesis("()");         // PASS!
        checkParenthesis("()()()");     // PASS!
        checkParenthesis("(())()");     // PASS!
        checkParenthesis("(((()))");    // FAIL!
    }
}
