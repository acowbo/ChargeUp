package fun.acowbo.simpleaccounting.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(SysUser)实体类
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @since 2025-03-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = -20311513867531772L;
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 加密密码
     */
    private String password;
    /**
     * 密码盐
     */
    private String salt;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户状态(1-正常,0-禁用)
     */
    private Integer status;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

}
