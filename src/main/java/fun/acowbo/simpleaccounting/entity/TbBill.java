package fun.acowbo.simpleaccounting.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户账单表(TbBill)实体类
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_bill")
public class TbBill extends BaseDo {

    private static final long serialVersionUID = 295413081597214387L;
    /**
     * 账单主键id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 账单类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 名称
     */
    private String name;

    /**
     * 账单时间
     */
    private LocalDateTime billTime;

    /**
     * 账单类型，0：支出，1：收入
     */
    private int inBill;

}
