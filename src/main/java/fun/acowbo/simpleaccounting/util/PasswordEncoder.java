package fun.acowbo.simpleaccounting.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * description: 密码工具类
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @version 1.0
 * @since 2025/3/25
 */
public class PasswordEncoder {
    /**
     * description: 生成随机盐
     *
     * @return java.lang.String
     * @since 2025/3/25
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }


    /**
     * description: 密码加盐hash
     *
     * @param password 密码
     * @param salt     盐
     * @return java.lang.String
     * @since 2025/3/25
     */
    public static String encodePassword(String password, String salt) {
        try {
            // 先将密码和盐拼接
            String passwordWithSalt = password + salt;

            // 使用SHA-256进行多次hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passwordWithSalt.getBytes());

            // 进行多次迭代增加破解难度
            for (int i = 0; i < 1000; i++) {
                hash = digest.digest(hash);
            }

            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("密码加密失败", e);
        }
    }

    /**
     * description: 验证密码
     *
     * @param inputPassword  用户输入的密码
     * @param storedPassword 数据库中存储的加密后的密码
     * @param salt           盐
     * @return boolean
     * @since 2025/3/25
     */
    public static boolean verifyPassword(String inputPassword, String storedPassword, String salt) {
        String encodedInputPassword = encodePassword(inputPassword, salt);
        return encodedInputPassword.equals(storedPassword);
    }

    /**
     * description: <h1>留个生成密码的口子</h1>
     *
     * @since 2025/5/9
     */
    public static void main(String[] args) {
        String password = "123456";
        String salt = generateSalt();
        String encodedPassword = encodePassword(password, salt);
        System.out.println("encodedPassword: " + encodedPassword);
        System.out.println("Salt: " + salt);
        System.out.println("Verify: " + verifyPassword(password, encodedPassword, salt));
    }
}