package com.cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String SHA_512_ALGORITHM = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static String getHashedPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_512_ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                hashedPassword.append(String.format("%02x", b));
            }

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Can't hash password using " + SHA_512_ALGORITHM, e);
        }
        return hashedPassword.toString();
    }

}
