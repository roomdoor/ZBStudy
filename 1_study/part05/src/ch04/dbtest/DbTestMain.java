package ch04.dbtest;

import java.util.Scanner;

public class DbTestMain {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        Scanner sc = new Scanner(System.in);

        memberService.dbSelect();

        String memberType = "email";
        System.out.print("아이디 입력 : ");
        String userId = sc.nextLine();
        System.out.print("비밀번호 입력 : ");
        String password = sc.nextLine();
        System.out.print("이름 입력 : ");
        String name = sc.nextLine();

        Member member = new Member(memberType, userId, password, name);


        memberService.register(member);



    }


}