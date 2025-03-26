package fun.acowbo.simpleaccounting.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author todoitbo
 * @date 2024/1/11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillTypeSumVO {

    private BigDecimal amount;

    private String name;

    private Long categoryId;
}
