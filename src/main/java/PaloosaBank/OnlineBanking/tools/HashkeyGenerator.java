package PaloosaBank.OnlineBanking.tools;

import java.util.UUID;

public class HashkeyGenerator {
    public static void main(String[] args) {
        System.out.println(generateString());
    }

    public static String generateString() {
        return UUID.randomUUID().toString();
    }
}