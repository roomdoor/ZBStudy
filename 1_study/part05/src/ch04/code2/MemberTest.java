package ch04.code2;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    public static void main(String[] args) {
        System.out.println("Test");

        Member member = new Member();

//        member.setUserId();
//        member.setEmail();
//        member.setName();
//        member.setPassword();
//        member.setUseSmsYN();
//        member.setH1();
//        member.setH2();
//        member.setH3();

        MemberService memberService = new MemberService();

        boolean result = memberService.register(member);
        boolean result1 = memberService.withdraw(member);

        List<Member> list = memberService.getList();
    }
}