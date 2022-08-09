package db;

import java.util.Scanner;

public class DbMain {
    public static void main(String[] args) {


        MemberService memberService = new MemberService();
        Member m =memberService.detail("email", "kim@daum.com");
        System.out.println(m.getMemberType());
        System.out.println(m.getUserId());
        System.out.println(m.getPassword());
        System.out.println(m.getName());
        System.out.println(m.getMobile_no());
        System.out.println(m.getMarketing_yn());
        System.out.println(m.getRegister_date());
//        memberService.list();


        Scanner sc = new Scanner(System.in);

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