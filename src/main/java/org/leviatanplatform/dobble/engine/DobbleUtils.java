package org.leviatanplatform.dobble.engine;

public class DobbleUtils {

    public static boolean isPrimeNumber(int num) {

        for (int i = 2; i <= num / 2; i++) {

            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
