package fun.acowbo.simpleaccounting.service;

import fun.acowbo.simpleaccounting.vo.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 用户账单表(TbBill)服务
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:52
 */
@Service
public interface ITbBillService {

    boolean saveBill(SaveTbBillReqVO tbBillVo);

    boolean deleteBill(Long id);

    List<TbBillRespVO> getBillList(GetTbBillReqVO getTbBillReqVo);

    /**
     * 获取当天花费总额
     *
     * @return 当天花费总额
     */
    BigDecimal getDailyExpense(boolean expense, Long userId);

    /**
     * 获取本周花费总额
     *
     * @return 本周花费总额
     */
    BigDecimal getWeeklyExpense(boolean expense, Long userId);

    /**
     * 获取本月花费总额
     *
     * @return 本月花费总额
     */
    BigDecimal getMonthlyExpense(boolean expense, long month, Long userId);

    /**
     * 获取当月账单数
     *
     * @return 本月账单数
     */

    Long getCount(Long userId);

    /**
     * 获取本年花费总额
     *
     * @return 本年花费总额
     */
    BigDecimal getYearlyExpense(boolean expense, Long userId);

    List<BillTypeSumVO> getTypeSum(Long userId);

    DetailRespVO getDetail(Long userId);

    BigDecimal calculateTotalExpense(boolean expense, Long userId);

    /**
     * description: 获取分类统计信息
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param inBill 类型
     * @return java.util.List<fun.acowbo.simpleaccounting.vo.BillTypeSumVO>
     * @since 2025/3/26
     */
    List<BillTypeSumVO> getTypeSumWithTotal(LocalDateTime startDate, LocalDateTime endDate, Integer inBill);

}
