package mate.academy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String CRYPTO_ALGORITHM = "SHA-512";
    private static final String HASH_STRING_TRANSFORMER = "%02x";
    private static final int LENGTH_BYTE_MASSIVE = 16;

    private HashUtil() {
    }

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[LENGTH_BYTE_MASSIVE];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CRYPTO_ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                hashPassword.append(String.format(HASH_STRING_TRANSFORMER, b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    String.format("Could not create hash using %s algorithm", CRYPTO_ALGORITHM), e);
        }
        return hashPassword.toString();
    }
}