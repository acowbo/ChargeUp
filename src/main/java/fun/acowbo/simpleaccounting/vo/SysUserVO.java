package fun.acowbo.simpleaccounting.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表(SysUser)实体类
 *
 * @author <a href="https://acowbo.fun">acowbo</a> * @since 2025-03-25 14:19:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUserVO {

    private String username;

    private String password;

}
