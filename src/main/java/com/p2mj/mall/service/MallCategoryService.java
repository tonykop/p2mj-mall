package com.p2mj.mall.service;

import com.p2mj.mall.api.vo.MjMallIndexCategoryVo;

import java.util.List;

public interface MallCategoryService {
    /**
     * 返回分类数据(首页调用)
     * @return
     */

    List<MjMallIndexCategoryVo> getCaategoriesForIndex();
}
