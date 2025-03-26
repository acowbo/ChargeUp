package fun.acowbo.simpleaccounting.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户账单表(TbBill)实体类
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveTbBillReqVO {

    /**
     * 账单主键id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime billTime;

    /**
     * 账单类型，0：支出，1：收入
     */
    private Integer inBill;

}
