package fun.acowbo.simpleaccounting.controller;


import cn.hutool.core.date.DateUtil;
import fun.acowbo.simpleaccounting.config.UserContext;
import fun.acowbo.simpleaccounting.exception.BoResult;
import fun.acowbo.simpleaccounting.service.ITbBillService;
import fun.acowbo.simpleaccounting.vo.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 用户账单表(TbBill)控制器
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:52
 */
@RestController
@RequestMapping(value = "TbBill")
@Validated
public class TbBillController {

    @Resource
    protected ITbBillService service;

    @PostMapping("/saveBill")
    public BoResult<Boolean> saveBill(@Validated @RequestBody SaveTbBillReqVO saveTbBillReqVo) {
        return BoResult.resultOk(service.saveBill(saveTbBillReqVo));
    }
    @DeleteMapping("/deleteBill")
    public BoResult<Boolean> deleteBill(Long id) {
        return BoResult.resultOk(service.deleteBill(id));
    }
    @GetMapping("/getBillList")
    public BoResult<List<TbBillRespVO>> getBillList(@Validated GetTbBillReqVO getTbBillReqVo) {
        return BoResult.resultOk(service.getBillList(getTbBillReqVo));
    }
    @GetMapping("/getDetail")
    public BoResult<DetailRespVO> getDetail() {
        return BoResult.resultOk(service.getDetail(UserContext.getUserId()));
    }

    @PostMapping("/getTypeSumWithTotal")
    public BoResult<List<BillTypeSumVO>> getTypeSumWithTotal(@RequestBody TypeSumWithTotalVO  typeSumWithTotalVO) {
        // date 转换成 LocalDateTime
        Date startTime = typeSumWithTotalVO.getStartTime();
        Date endTime = typeSumWithTotalVO.getEndTime();
        Integer type = typeSumWithTotalVO.getType();
        LocalDateTime startLocalDateTime = DateUtil.toLocalDateTime(startTime);
        LocalDateTime endLocalDateTime = DateUtil.toLocalDateTime(endTime);
        List<BillTypeSumVO> typeSumWithTotal = service.getTypeSumWithTotal(startLocalDateTime, endLocalDateTime, type);
        return BoResult.resultOk(typeSumWithTotal);
    }
}
