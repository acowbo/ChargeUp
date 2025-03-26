package fun.acowbo.simpleaccounting.convert;


import fun.acowbo.simpleaccounting.entity.TbBill;
import fun.acowbo.simpleaccounting.vo.TbBillRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author todoitbo
 * @date 2024/1/3
 */
@Mapper
public interface TbBillConvert {

    TbBillConvert INSTANCE = Mappers.getMapper(TbBillConvert.class);

    TbBillRespVO convert(TbBill tbBillVo);

    List<TbBillRespVO> convertList(List<TbBill> list);

}
