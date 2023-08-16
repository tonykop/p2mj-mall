package com.p2mj.mall.controller.param;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class SaveCartItemParam  implements Serializable {

    @ApiModelProperty("商品数量")
    private Integer goodsCount;

    @ApiModelProperty("商品id")
    private Long goodsId;
}
