package com.p2mj.mall.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IndexInfoVo implements Serializable {

    @ApiModelProperty("轮播图{列表}")
    private List<MjMallIndexCarouselVo> carousels;

    @ApiModelProperty("首页热销商品（列表）")
    private  List<MjMallIndexConfigGoodsVO> hotGoodses;

    @ApiModelProperty("首页新品推荐(列表)")
    private  List<MjMallIndexConfigGoodsVO> newGoodses;

    @ApiModelProperty("首页推荐商品(列表)")
    private  List<MjMallIndexConfigGoodsVO> recommendGoodses;
}
