package fun.acowbo.simpleaccounting.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.acowbo.simpleaccounting.config.UserContext;
import fun.acowbo.simpleaccounting.entity.TbCategory;
import fun.acowbo.simpleaccounting.mapper.TbCategoryMapper;
import fun.acowbo.simpleaccounting.service.ITbCategoryService;
import fun.acowbo.simpleaccounting.vo.SaveTbCategoryVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 账单分类表(TbCategory)服务
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:52
 */
@Service
public class TbCategoryServiceImpl implements ITbCategoryService {

    @Resource
    protected TbCategoryMapper mapper;

    @Override
    public boolean deleteCategory(Long id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public TbCategory saveCategory(SaveTbCategoryVO tbCategoryVo) {
        TbCategory bean = BeanUtil.toBean(tbCategoryVo, TbCategory.class);
        bean.setUserId(UserContext.getUserId());
        bean.setType(tbCategoryVo.getType());
        Long selectCount = mapper.selectCount(new LambdaQueryWrapper<TbCategory>().eq(TbCategory::getName, bean.getName()));
        if (selectCount > 0){
            throw new RuntimeException("分类名称已存在");
        }
        if (bean.getId() == null || bean.getId() == 0){
            return mapper.insert(bean) > 0 ? bean : null;
        }
        return mapper.updateById(bean) > 0 ? bean : null;
    }

    @Override
    public List<TbCategory> getCategoryList(Long userId) {
        LambdaQueryWrapper<TbCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TbCategory::getUserId, userId).select(TbCategory::getId, TbCategory::getName, TbCategory::getType);
        return mapper.selectList(wrapper);
    }

}
