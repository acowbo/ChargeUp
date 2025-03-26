package fun.acowbo.simpleaccounting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * description: WebMvcConfig配置
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @version 1.0
 * @since 2025/3/25
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                // 配置需要拦截的路径
                .addPathPatterns("/**")
                // 配置排除的路径
                .excludePathPatterns("/login", "/SysUser/login","/static/**");
    }
}