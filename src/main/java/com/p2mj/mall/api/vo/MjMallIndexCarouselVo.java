package com.p2mj.mall.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页轮播图VO
 */
@Data
public class MjMallIndexCarouselVo implements Serializable {

    @ApiModelProperty("轮播图片地址")
    private String carouselUrl;

    @ApiModelProperty("轮播图单机后跳转的地址")
    private String redirectUrl;
}
