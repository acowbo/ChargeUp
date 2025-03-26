package fun.acowbo.simpleaccounting.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author todoitbo
 * @date 2024/1/4
 */
@Data
@Builder
public class DetailRespVO {

    /**
     * 日收入
     */
    private BigDecimal dailyIncome;

    /**
     * 日支出
     */
    private BigDecimal dailyExpense;

    /**
     * 周收入
     */
    private BigDecimal weeklyIncome;

    /**
     * 周支出
     */
    private BigDecimal weeklyExpense;

    /**
     * 月收入
     */
    private BigDecimal monthlyIncome;

    /**
     * 月支出
     */
    private BigDecimal monthlyExpense;

    /**
     * 年收入
     */
    private BigDecimal yearlyIncome;

    /**
     * 年支出
     */
    private BigDecimal yearlyExpense;

    /**
     * 总支出
     */
    private BigDecimal totalExpense;

    /**
     * 总收入
     */
    private BigDecimal totalIncome;


}
