package com.p2mj.mall.service;

import com.p2mj.mall.api.vo.MjMallIndexConfigGoodsVO;

import java.util.List;

public interface MallIndexConfigService {
    /**
     * 返回固定数量的首页配置商品对象（首页调用）
     */

    List<MjMallIndexConfigGoodsVO>  getConfigGoodsForIndex(int configType,int number);
}
