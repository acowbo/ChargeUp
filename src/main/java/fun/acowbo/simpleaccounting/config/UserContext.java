package fun.acowbo.simpleaccounting.config;

/**
 * description: 存储用户信息
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @version 1.0
 * @since 2025/3/25
 */
public class UserContext {
    private static final ThreadLocal<Long> USER_ID_THREAD_LOCAL = new ThreadLocal<>();

    // 设置用户 ID
    public static void setUserId(Long userId) {
        USER_ID_THREAD_LOCAL.set(userId);
    }

    // 获取用户 ID
    public static Long getUserId() {
        return USER_ID_THREAD_LOCAL.get();
    }

    // 清除用户 ID
    public static void clear() {
        USER_ID_THREAD_LOCAL.remove();
    }
}