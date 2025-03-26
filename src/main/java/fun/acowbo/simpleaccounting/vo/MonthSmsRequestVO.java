package fun.acowbo.simpleaccounting.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author todoitbo
 * @date 2024/2/21
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthSmsRequestVO {

    private String time;

    private BigDecimal monthPay;

    private BigDecimal differencePay;

    private BigDecimal yearPay;
}
