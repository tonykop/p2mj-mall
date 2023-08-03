package com.p2mj.mall.service;

import com.p2mj.mall.api.vo.MjMallIndexCarouselVo;

import java.util.List;

public interface MallCarouselService {
    /**
     * 返回固定数量的轮播图对象（首页调用）
     */

    List<MjMallIndexCarouselVo> getCarouselsForIndex(int number);
}
