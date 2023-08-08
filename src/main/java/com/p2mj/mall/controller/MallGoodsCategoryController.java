package com.p2mj.mall.controller;


import com.p2mj.mall.api.vo.MjMallIndexCategoryVo;
import com.p2mj.mall.common.MjMallException;
import com.p2mj.mall.common.ServiceResultEnum;
import com.p2mj.mall.service.MallCategoryService;
import com.p2mj.mall.util.Result;
import com.p2mj.mall.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "v1",tags = "Mj商城分类页面接口")
@RequestMapping("/api/v1")
public class MallGoodsCategoryController {

    @Resource
    private MallCategoryService mallCategoryService;

    @GetMapping("/categories")
    @ApiOperation(value = "获取分类数据",notes = "分类页面使用")
    public Result<List<MjMallIndexCategoryVo>> getCategories(){

        List<MjMallIndexCategoryVo> categories = mallCategoryService.getCaategoriesForIndex();
        if(CollectionUtils.isEmpty(categories)){
            MjMallException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }

        return ResultGenerator.genSuccessResult(categories);
    }
}
