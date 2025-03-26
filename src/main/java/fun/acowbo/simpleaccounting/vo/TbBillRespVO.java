package fun.acowbo.simpleaccounting.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author todoitbo
 * @date 2024/1/3
 */
@Data
public class TbBillRespVO {

    /**
     * 账单ID，主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 账单类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    /**
     * 账单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime billTime;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 名称
     */
    private String name;

    /**
     * 账单类别名称
     */
    private String categoryName;

    /**
     * 是否收入
     */
    private int inBill;
}
