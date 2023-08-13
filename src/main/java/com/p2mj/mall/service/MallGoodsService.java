package com.p2mj.mall.service;

import com.p2mj.mall.entity.MjMallGoods;
import com.p2mj.mall.util.PageQueryUtil;
import com.p2mj.mall.util.PageResult;

import java.util.Collection;

public interface MallGoodsService {

    /**
     * 商品搜索
     *
     */

    PageResult searchMjMallGoods(PageQueryUtil pageQueryUtil);

    MjMallGoods getMjMallGoodsById(Long id);
}
