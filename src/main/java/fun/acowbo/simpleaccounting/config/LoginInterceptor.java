package fun.acowbo.simpleaccounting.config;


import fun.acowbo.simpleaccounting.entity.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * description: 拦击器
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @version 1.0
 * @since 2025/3/25
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查Session中是否有用户信息
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");

        // 如果未登录，重定向到登录页面
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        UserContext.setUserId(user.getId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完成后清除 ThreadLocal，避免内存泄漏
        UserContext.clear();
    }
}