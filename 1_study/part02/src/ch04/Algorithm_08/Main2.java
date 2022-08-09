package ch04.Algorithm_08;
// 거스름돈 문제

import java.util.HashMap;

public class Main2 {
    public static void getChangeCoins(int receivedMoney, int price) {
        int[] coins = {500, 100, 50, 10, 5, 1};
        HashMap<Integer, Integer> result = new HashMap<>();

        int change = receivedMoney - price;
        int count = 0;

        for (int coin : coins) {
            count += change / coin;
            System.out.println(coin + "원 동전 " + change / coin + "개");
            change %= coin;
        }

        System.out.println(count);
    }

    public static void main(String[] args) {
        // Test code
        getChangeCoins(1000, 100);
        getChangeCoins(1234, 500);
    }
}
