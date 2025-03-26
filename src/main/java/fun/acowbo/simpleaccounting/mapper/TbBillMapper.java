package fun.acowbo.simpleaccounting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.acowbo.simpleaccounting.entity.TbBill;
import fun.acowbo.simpleaccounting.vo.BillTypeSumVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户账单表(TbBill)数据库访问层
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:51
 */
public interface TbBillMapper extends BaseMapper<TbBill> {

    @Select("SELECT COALESCE(SUM(amount), 0) " +
            "FROM tb_bill " +
            "WHERE bill_time >= #{startDate} AND bill_time <= #{endDate} AND in_bill = #{type} AND user_id = #{userId}")
    BigDecimal findByTimestampBetween(@Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate,
                                      @Param("type") int type,
                                      @Param("userId") Long userId);

    @Select("SELECT COALESCE(SUM(amount), 0) " +
            "FROM tb_bill " +
            "WHERE in_bill = #{expense} and user_id = #{userId}")
    BigDecimal getDail(@Param("expense") int expense, @Param("userId") Long userId);

    @Select("SELECT " +
            "SUM( tb.amount ) amount, " +
            "tb.category_id categoryId, " +
            "tc.`name` " +
            "FROM " +
            "tb_bill tb " +
            "LEFT JOIN tb_category tc ON tc.id = tb.category_id " +
            "WHERE tb.bill_time>= #{startDate} AND tb.bill_time <= #{endDate} and tb.user_id = #{userId} " +
            "GROUP BY " +
            "category_id")
    List<BillTypeSumVO> getTypeSum(@Param("startDate") LocalDateTime startDate,
                                   @Param("endDate") LocalDateTime endDate,
                                   @Param("userId") Long userId);


    @Select("SELECT " +
            "IFNULL(tc.name, '总金额') AS name, " +
            "SUM(tb.amount) AS amount " +
            "FROM " +
            "tb_bill tb " +
            "LEFT JOIN tb_category tc ON tc.id = tb.category_id " +
            "WHERE " +
            "tb.bill_time >= #{startDate} " +
            "AND tb.bill_time <= #{endDate} " +
            "AND tb.user_id = #{userId} " +
            "AND tb.in_bill = #{inBill} " +
            "GROUP BY " +
            "tc.name " +
            "WITH ROLLUP")
    List<BillTypeSumVO> getTypeSumWithTotal(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate,
                                            @Param("userId") Long userId,
                                            @Param("inBill") Integer inBill);

}

