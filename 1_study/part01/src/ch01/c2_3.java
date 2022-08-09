package ch01;

public class c2_3 {
    public static void main(String[] args) {

//      1. 자료형 - 문자열
        System.out.println("== 문자열 ==");


//      1-1. equals


//      1-2. indexOf
        String s6 = "12345 56785";
        System.out.println(s6.indexOf("5"));
        System.out.println(s6.indexOf("5", s6.indexOf("5") + 1));

//      1-3. replace
        String s7 = s6.replace("23", "k");
        System.out.println(s7);


//      1-4. substring
        System.out.println(s7.substring(0,3));
        System.out.println(s7.substring(0,s7.indexOf("6") + 1));

//      1-5. toUpperCase


//      2. 자료형 - StringBuffer
//      문자열이 많이 바뀔때 사용하면 메모리 절감 가능   왜??  String 으로 하면 바뀔 떄마다 새로운 객채를 사용하기 때문에 메모리에 부담이 됨
        System.out.println("== StringBuffer ==");


//      3. 자료형 - 배열
        System.out.println("== 배열 ==");
    }
}
