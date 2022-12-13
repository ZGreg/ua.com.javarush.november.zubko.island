package util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomMethodsHolder {        //ThrLocRndMethodHolder

    private RandomMethodsHolder() {
    }

    public static int getRndNum(int num) {
        return ThreadLocalRandom.current().nextInt(num);
    }

    public static int getRndNum(int from, int num) {
        return ThreadLocalRandom.current().nextInt(from, num);
    }
}
