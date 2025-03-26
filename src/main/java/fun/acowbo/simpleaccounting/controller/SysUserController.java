package fun.acowbo.simpleaccounting.controller;

import fun.acowbo.simpleaccounting.entity.SysUser;
import fun.acowbo.simpleaccounting.exception.BoResult;
import fun.acowbo.simpleaccounting.service.ISysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 用户表(SysUser)控制器
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @since 2025-03-25
 */
@RestController
@RequestMapping(value = "SysUser")
public class SysUserController {

    @Resource
    private ISysUserService service;

    @PostMapping("/login")
    public BoResult<SysUser> login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session
    ) {

        SysUser sysUser = service.login(username, password);
        session.setAttribute("user", sysUser);
        return BoResult.resultOk(sysUser);

    }

}
