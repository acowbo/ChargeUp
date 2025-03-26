package fun.acowbo.simpleaccounting.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class GetTbBillReqVO {

    /**
     * 账单主键id
     */
    private Long id;

    /**
     * 账单类别id
     */
    private Long categoryId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账单类别名称
     */
    private Long categoryName;

    /**
     * 最小金额
     */
    private Double minAmount;

    /**
     * 最大金额
     */
    private Double maxAmount;

    /**
     * 名称
     */
    private String name;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 账单出账
     */
    private int inBill;

    /**
     * 账单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime belongTime;

}
