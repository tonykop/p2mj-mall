package com.p2mj.mall.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页配置商品VO
 */
@Data
public class MjMallIndexConfigGoodsVO implements Serializable {

    @ApiModelProperty("商品ID")
    private Long goodsId;

   @ApiModelProperty("商品名称")
    private String goodsName;

   // private String goodsIntro;

    @ApiModelProperty("商品图片地址")
    private String goodsCoverImg;

    @ApiModelProperty("商品价格")
    private Integer sellingPrice;

    //private String tag;
}
