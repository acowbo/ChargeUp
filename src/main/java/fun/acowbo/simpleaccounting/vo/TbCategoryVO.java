package fun.acowbo.simpleaccounting.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 账单分类表(TbCategory)实体类
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbCategoryVO {

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除标识
     */
    private Integer isDeleted;

    /**
     * 上传人
     */
    private Long createBy;

    /**
     * 更新人
     */
    private Long updateBy;
}
