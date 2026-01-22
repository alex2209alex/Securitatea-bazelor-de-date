package ro.unibuc.fmi.sbd.service;

import java.security.SecureRandom;

public class GeneratorIban {
    private static final SecureRandom random = new SecureRandom();
    private static final String PREFIX = "RO";

    private GeneratorIban() {
    }

    public static String genereazaIban() {
        StringBuilder sb = new StringBuilder(PREFIX);
        for (int i = 0; i < 22; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
