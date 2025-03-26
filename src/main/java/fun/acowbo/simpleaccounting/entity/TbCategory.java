package fun.acowbo.simpleaccounting.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

/**
 * 账单分类表(TbCategory)实体类
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_category")
public class TbCategory extends BaseDo {

    /**
     * 分类ID，主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类类型，父级：0-支出，1-收入
     */
    private Integer type;
    /**
     * 默认类型为1，否则为用户Id
     */
    private Long userId;
    /**
     * 父级id
     */
    private Long parentId;

}
