package fun.acowbo.simpleaccounting.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class SaveTbCategoryVO {

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

}
