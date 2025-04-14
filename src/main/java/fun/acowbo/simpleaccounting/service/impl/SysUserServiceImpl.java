package fun.acowbo.simpleaccounting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.acowbo.simpleaccounting.entity.SysUser;
import fun.acowbo.simpleaccounting.exception.BusinessException;
import fun.acowbo.simpleaccounting.mapper.SysUserMapper;
import fun.acowbo.simpleaccounting.service.ISysUserService;
import fun.acowbo.simpleaccounting.util.PasswordEncoder;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 用户表(SysUser)服务
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @since 2025-03-25
 */
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    protected SysUserMapper mapper;

    @Override
    public SysUser login(String username, String password) {
        SysUser sysUser = mapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            // 防暴力
            throw new BusinessException("用户名或密码错误");
        }
        boolean verifyPassword = PasswordEncoder.verifyPassword(password, sysUser.getPassword(), sysUser.getSalt());
        if (!verifyPassword) {
            throw new BusinessException("用户名或密码错误");
        }
        return sysUser;
    }

    public void initializeDefaultUser() {
        // 从环境变量获取用户信息，如果没有则使用默认值
        String defaultUsername = System.getenv("DEFAULT_ADMIN_USERNAME");
        String defaultPassword = System.getenv("DEFAULT_ADMIN_PASSWORD");

        // 如果没有设置环境变量，则使用默认值
        defaultUsername = StringUtils.hasText(defaultUsername) ? defaultUsername : "admin";
        defaultPassword = StringUtils.hasText(defaultPassword) ? defaultPassword : "123456";

        // 检查用户是否已存在
        Long userCount = mapper.selectCount(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, defaultUsername));

        // 如果用户不存在，则创建
        if (userCount == 0) {
            String salt = PasswordEncoder.generateSalt();
            String encodedPassword = PasswordEncoder.encodePassword(defaultPassword, salt);

            SysUser user = new SysUser();
            user.setUsername(defaultUsername);
            user.setPassword(encodedPassword);
            user.setSalt(salt);

            mapper.insert(user);
        }
    }
}
