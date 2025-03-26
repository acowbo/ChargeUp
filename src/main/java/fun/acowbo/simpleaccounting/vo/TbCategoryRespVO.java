package fun.acowbo.simpleaccounting.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

/**
 * @author todoitbo
 * @date 2024/1/3
 */
@Data
@ToString(callSuper = true)
public class TbCategoryRespVO {

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
