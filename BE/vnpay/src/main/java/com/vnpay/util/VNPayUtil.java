package com.vnpay.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class VNPayUtil {

    private VNPayUtil() {
    }

    /**
     * Generates an HMAC-SHA-512 hash of the given data using the specified key.
     *
     * @param key  The secret key used for the HMAC generation.
     * @param data The data to be hashed.
     * @return The hexadecimal representation of the HMAC-SHA-512 hash.
     * @throws NullPointerException If either the key or data is null.
     */
    public static String hmacSHA512(final String key, final String data) {
        // Validate input parameters
        if (key == null || data == null) {
            throw new NullPointerException("Key and data must not be null.");
        }

        try {
            // Initialize HMAC-SHA-512 instance
            Mac hmac512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            hmac512.init(secretKey);

            // Compute HMAC hash
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] hashBytes = hmac512.doFinal(dataBytes);

            // Convert hash bytes to hexadecimal string
            StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b & 0xff));
            }

            return hexString.toString();

        } catch (Exception ex) {
            return "";
        }
    }
}
