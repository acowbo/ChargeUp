package fun.acowbo.simpleaccounting.controller;


import fun.acowbo.simpleaccounting.config.UserContext;
import fun.acowbo.simpleaccounting.convert.TbCategoryConvert;
import fun.acowbo.simpleaccounting.entity.TbCategory;
import fun.acowbo.simpleaccounting.exception.BoResult;
import fun.acowbo.simpleaccounting.service.ITbCategoryService;
import fun.acowbo.simpleaccounting.vo.SaveTbCategoryVO;
import fun.acowbo.simpleaccounting.vo.TbCategoryRespVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账单分类表(TbCategory)控制器
 *
 * @author todoitbo
 * @since 2024-01-03 13:57:53
 */
@RestController
@RequestMapping(value = "TbCategory")
@Validated
public class TbCategoryController{

    @Resource
    protected ITbCategoryService service;

    @GetMapping("/getCategoryList")
    public BoResult<List<TbCategoryRespVO>> getCategoryList(){
        return BoResult.resultOk(TbCategoryConvert.INSTANCE.convertList(service.getCategoryList(UserContext.getUserId())));
    }
    @DeleteMapping("/deleteCategory")
    public BoResult<Boolean> deleteCategory(Long id){
        return BoResult.resultOk(service.deleteCategory(id));
    }
    @PostMapping("/saveCategory")
    public BoResult<TbCategory> saveCategory(@RequestBody SaveTbCategoryVO saveTbCategoryVo){
        return BoResult.resultOk(service.saveCategory(saveTbCategoryVo));
    }
}
