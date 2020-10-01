package aval.ua.conference.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class IdGenerator {
    private static SecureRandom random = new SecureRandom();

    public static Long newId() {
        return Math.abs(new BigInteger(20, random).longValue());
    }
}
