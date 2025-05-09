package fun.acowbo.simpleaccounting.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.acowbo.simpleaccounting.config.UserContext;
import fun.acowbo.simpleaccounting.convert.TbBillConvert;
import fun.acowbo.simpleaccounting.entity.TbBill;
import fun.acowbo.simpleaccounting.entity.TbCategory;
import fun.acowbo.simpleaccounting.mapper.TbBillMapper;
import fun.acowbo.simpleaccounting.service.ITbBillService;
import fun.acowbo.simpleaccounting.service.ITbCategoryService;
import fun.acowbo.simpleaccounting.util.Assert;
import fun.acowbo.simpleaccounting.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户账单表(TbBill)服务
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:51
 */
@Service
public class TbBillServiceImpl implements ITbBillService {

    @Resource
    private TbBillMapper mapper;

    @Resource
    private ITbCategoryService tbCategoryService;


    @Override
    public boolean saveBill(SaveTbBillReqVO tbBillVo) {
        TbBill bean = BeanUtil.toBean(tbBillVo, TbBill.class);
        bean.setUserId(UserContext.getUserId());
        if (bean.getId() == null || bean.getId() == 0) {
            return mapper.insert(bean) > 0;
        }
        return mapper.updateById(bean) > 0;
    }

    @Override
    public boolean deleteBill(Long id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public List<TbBillRespVO> getBillList(GetTbBillReqVO getTbBillReqVo) {
        LambdaQueryWrapper<TbBill> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(getTbBillReqVo.getId() != null, TbBill::getId, getTbBillReqVo.getId())
                .eq(getTbBillReqVo.getCategoryId() != null, TbBill::getCategoryId, getTbBillReqVo.getCategoryId())
                .eq(TbBill::getUserId, UserContext.getUserId())
                .between(getTbBillReqVo.getStartTime() != null, TbBill::getBillTime, getTbBillReqVo.getStartTime(), getTbBillReqVo.getEndTime())
                .like(getTbBillReqVo.getName() != null, TbBill::getName, getTbBillReqVo.getName())
                .between(getTbBillReqVo.getMinAmount() != null, TbBill::getAmount, getTbBillReqVo.getMinAmount(), getTbBillReqVo.getMaxAmount())
                .apply(getTbBillReqVo.getBelongTime() != null, "DATE_FORMAT(bill_time, '%Y-%m-%d') = DATE_FORMAT({0}, '%Y-%m-%d')", getTbBillReqVo.getBelongTime())
                .orderByDesc(TbBill::getBillTime);
        List<TbBillRespVO> tbBillRespVos = new ArrayList<>();
        List<TbBill> tbBills = mapper.selectList(lambdaQueryWrapper);
        if (!Assert.isEmpty(tbBills)) {
            List<TbCategory> categoryList = tbCategoryService.getCategoryList(UserContext.getUserId());
            tbBillRespVos = TbBillConvert.INSTANCE.convertList(tbBills);
            tbBillRespVos.forEach(tbBill -> {
                for (TbCategory tbCategory : categoryList) {
                    if (tbBill.getCategoryId().equals(tbCategory.getId())) {
                        tbBill.setCategoryName(tbCategory.getName());
                    }
                }
            });

        }
        return tbBillRespVos;

    }

    // 获取当天花费总额
    @Override
    public BigDecimal getDailyExpense(boolean expense, Long userId) {
        LocalDateTime todayStart = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime todayEnd = LocalDateTime.now().with(LocalTime.MAX);

        return mapper.findByTimestampBetween(todayStart, todayEnd, expense ? 0 : 1, userId);
    }

    // 获取本周花费总额
    @Override
    public BigDecimal getWeeklyExpense(boolean expense, Long userId) {
        LocalDateTime weekStart = LocalDateTime.now().with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        LocalDateTime weekEnd = LocalDateTime.now().with(DayOfWeek.SUNDAY).with(LocalTime.MAX);

        return mapper.findByTimestampBetween(weekStart, weekEnd, expense ? 0 : 1, userId);
    }

    // 获取本月花费总额
    @Override
    public BigDecimal getMonthlyExpense(boolean expense, long month, Long userId) {
        LocalDateTime monthStart = LocalDateTime.now().minusMonths(month).withDayOfMonth(1).with(LocalTime.MIN);
        LocalDateTime monthEnd = LocalDateTime.now().minusMonths(month).withDayOfMonth(1).plusMonths(1).minusNanos(1);

        return mapper.findByTimestampBetween(monthStart, monthEnd, expense ? 0 : 1, userId);
    }

    // 获取当月账单条数
    @Override
    public Long getCount(Long userId) {
        LocalDateTime monthStart = LocalDateTime.now().withDayOfMonth(1).with(LocalTime.MIN);
        LocalDateTime monthEnd = LocalDateTime.now().withDayOfMonth(1).plusMonths(1).minusNanos(1);
        LambdaQueryWrapper<TbBill> tbBillLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbBillLambdaQueryWrapper.between(TbBill::getBillTime, monthStart, monthEnd).eq(TbBill::getUserId, userId).eq(TbBill::getIsDeleted, 0);
        return mapper.selectCount(tbBillLambdaQueryWrapper);
    }

    @Override
    public List<BillTypeSumVO> getTypeSum(Long userId) {
        LocalDateTime monthStart = LocalDateTime.now().withDayOfMonth(1).with(LocalTime.MIN);
        LocalDateTime monthEnd = LocalDateTime.now().withDayOfMonth(1).plusMonths(1).minusNanos(1);
        return mapper.getTypeSum(monthStart, monthEnd, userId);
    }

    // 获取本年花费总额
    @Override
    public BigDecimal getYearlyExpense(boolean expense, Long userId) {
        LocalDateTime yearStart = LocalDateTime.now().withDayOfYear(1).with(LocalTime.MIN);
        LocalDateTime yearEnd = LocalDateTime.now().withDayOfYear(1).plusYears(1).minusNanos(1);

        return mapper.findByTimestampBetween(yearStart, yearEnd, expense ? 0 : 1, userId);
    }

    @Override
    public DetailRespVO getDetail(Long userId) {
        return DetailRespVO.builder()
                .dailyExpense(getDailyExpense(true, userId))
                .dailyIncome(getDailyExpense(false, userId))
                .monthlyExpense(getMonthlyExpense(true, 0, userId))
                .monthlyIncome(getMonthlyExpense(false, 0, userId))
                .weeklyExpense(getWeeklyExpense(true, userId))
                .weeklyIncome(getWeeklyExpense(false, userId))
                .yearlyExpense(getYearlyExpense(true, userId))
                .yearlyIncome(getYearlyExpense(false, userId))
                .totalExpense(calculateTotalExpense(true, userId))
                .totalIncome(calculateTotalExpense(false, userId))
                .build();
    }

    // 计算总花费
    @Override
    public BigDecimal calculateTotalExpense(boolean expense, Long userId) {
        return mapper.getDail(expense ? 0 : 1, userId);
    }

    @Override
    public List<BillTypeSumVO> getTypeSumWithTotal(LocalDateTime startDate, LocalDateTime endDate, Integer inBill) {
        return mapper.getTypeSumWithTotal(startDate, endDate, UserContext.getUserId(), inBill);

    }
}
