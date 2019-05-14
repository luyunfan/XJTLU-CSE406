package per.yunfan.cse406.musicplayer.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Password encrypting utility class
 */
public final class PasswordUtils {

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(PasswordUtils.class);

    /**
     * Utility class may not create an instance
     */
    private PasswordUtils() {
    }

    /**
     * Encrypt the password by MD5 algorithm
     *
     * @param password Need encrypt password
     * @return The ciphertext
     */
    public static String encryptedByMD5(String password) {
        StringBuilder result = new StringBuilder();
        try {
            byte[] pass = MessageDigest
                    .getInstance("MD5")
                    .digest(password.getBytes());

            for (byte b : pass) {
                int salt = b & 0xCA; //Salt
                String saltString = Integer.toHexString(salt);
                if (1 == saltString.length()) {
                    result.append("0");
                }
                result.append(saltString);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.error("No MD5 Algorithm in this System! ", e);
            throw new RuntimeException("Could not encrypt the user password in this system!", e);
        }
    }

    /**
     * Create an user token
     *
     * @param userId user ID
     * @return An user token
     */
    public static String createToken(int userId) {
        String tokenRaw = userId + "_" + UUID.randomUUID().toString().replace("-", "");
        return encryptedByMD5(tokenRaw);
    }

}
