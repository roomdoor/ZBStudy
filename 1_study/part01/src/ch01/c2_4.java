package ch01;

import java.util.ArrayList;
import java.util.Comparator;

public class c2_4 {
    public static void main(String[] args) {

//      1. 자료형 - 리스트
        System.out.println("== 리스트 ==");
        ArrayList l1 = new ArrayList();

//      1-1. add
        l1.add(1);
        l1.add("hello");
        l1.add(2);
        l1.add(3);
        l1.add(4);
        l1.add(5);
        l1.add(6);
        System.out.println("l1 = " + l1);

//      1-2. get


//      1-3. size

//      1-4. remove
        System.out.println(l1.remove(0));
        System.out.println("l1 = " + l1);
        System.out.println(l1.remove(Integer.valueOf(2)));
        System.out.println("l1 = " + l1);

//      1-5. clear


//      1-6. sort
        ArrayList l2 = new ArrayList();
        l2.add(6);
        l2.add(2);
        l2.add(7);
        l2.add(5);
        l2.add(2);

        l2.sort(Comparator.naturalOrder());
        System.out.println("l2 = " + l2);
        l2.sort(Comparator.reverseOrder());
        System.out.println("l2 = " + l2);

//      1-7. contains


//      2. Maps
        System.out.println("== Maps ==");


//      2-1. put


//      2-2. get


//      2-3. size

//      2-4. remove


//      2-5. containsKey


//      3. Generics
        System.out.println("== Generics ==");


    }

}
