package fun.acowbo.simpleaccounting.convert;


import fun.acowbo.simpleaccounting.entity.TbCategory;
import fun.acowbo.simpleaccounting.vo.TbCategoryRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author todoitbo
 * @date 2024/1/3
 */
@Mapper
public interface TbCategoryConvert {

    TbCategoryConvert INSTANCE = Mappers.getMapper(TbCategoryConvert.class);

    List<TbCategoryRespVO> convertList(List<TbCategory> list);

    TbCategoryRespVO convert(TbCategory list);
}
