package util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    private Randomizer() {
    }

    public static int getRndNum(int num) {
        return ThreadLocalRandom.current().nextInt(num);
    }

    public static int getRndNum(int from, int num) {
        return ThreadLocalRandom.current().nextInt(from, num);
    }

    public static boolean flipCoin(){
        return ThreadLocalRandom.current().nextBoolean();
    }
}
