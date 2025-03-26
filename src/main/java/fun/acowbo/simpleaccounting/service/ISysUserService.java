package fun.acowbo.simpleaccounting.service;

import fun.acowbo.simpleaccounting.entity.SysUser;
import org.springframework.stereotype.Service;


/**
 * 用户表(SysUser)服务
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @since 2025-03-25
 */
@Service
public interface ISysUserService{

    /**
     * description: 用户登录
     * @param username 用户名
     * @param password 密码
     * @return SysUser 用户信息
     * @since 2025/3/25
     */
    SysUser login(String username, String password);

}
