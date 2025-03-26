package fun.acowbo.simpleaccounting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.acowbo.simpleaccounting.entity.SysUser;
import fun.acowbo.simpleaccounting.exception.BusinessException;
import fun.acowbo.simpleaccounting.mapper.SysUserMapper;
import fun.acowbo.simpleaccounting.service.ISysUserService;
import fun.acowbo.simpleaccounting.util.PasswordEncoder;
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
}
