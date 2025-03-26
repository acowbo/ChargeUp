package fun.acowbo.simpleaccounting.service;


import fun.acowbo.simpleaccounting.entity.TbCategory;
import fun.acowbo.simpleaccounting.vo.SaveTbCategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 账单分类表(TbCategory)服务
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:53
 */
@Service
public interface ITbCategoryService {


    /**
     * description: 删除分类
     * @param id 分类id
     * @return boolean
     * @since 2025/3/26
     */
    boolean deleteCategory(Long id);

    /**
     * description: 保存分类
     * @param tbCategoryVo 分类信息
     * @return fun.acowbo.simpleaccounting.entity.TbCategory
     * @since 2025/3/26
     */
    TbCategory saveCategory(SaveTbCategoryVO tbCategoryVo);

    /**
     * description: 获取分类列表
     * @param userId 用户id
     * @return java.util.List<fun.acowbo.simpleaccounting.entity.TbCategory>
     * @since 2025/3/26
     */
    List<TbCategory> getCategoryList(Long userId);


}
