package com.example.account;

import java.util.Random;

public class Tep {
    public static void main(String[] args) {


        for (int i = 0; i < 1000; i++) {
            StringBuilder num = new StringBuilder();
            Random random = new Random();
            for (int j = 0; j < 10; j++) {
                num.append(random.nextInt(10));
            }
            System.out.println(num);
        }
    }
}
