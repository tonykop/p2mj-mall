package com.p2mj.mall.controller;


import com.p2mj.mall.api.vo.IndexInfoVo;
import com.p2mj.mall.api.vo.MjMallIndexCarouselVo;
import com.p2mj.mall.api.vo.MjMallIndexConfigGoodsVO;
import com.p2mj.mall.common.Constants;
import com.p2mj.mall.common.IndexConfigTypeEnum;
import com.p2mj.mall.service.MallCarouselService;
import com.p2mj.mall.service.MallIndexConfigService;
import com.p2mj.mall.util.Result;
import com.p2mj.mall.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "v1",tags = "Mj商城首页接口")
@RequestMapping("/api/v1")
public class MallIndexController {

    @Resource
    private MallCarouselService mallCarouselService;

    @Resource
    private MallIndexConfigService mallIndexConfigService;
    @GetMapping("/index-infos")
    @ApiOperation(value = "获取首页数据",notes = "轮播图，新品，推荐等")
    public Result<IndexInfoVo> indexInfo(){

        IndexInfoVo indexInfoVo = new IndexInfoVo();
        List<MjMallIndexCarouselVo> carousels = mallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);

        List<MjMallIndexConfigGoodsVO> hotGoodses = mallIndexConfigService.getConfigGoodsForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(),Constants.INDEX_GOODS_HOT_NUMBER);

        List<MjMallIndexConfigGoodsVO> newGoodees = mallIndexConfigService.getConfigGoodsForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(),Constants.INDEX_GOODS_NEW_NUMBER);

        List<MjMallIndexConfigGoodsVO> recommendGoodses = mallIndexConfigService.getConfigGoodsForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(),Constants.INDEX_GOODS_RECOMMOND_NUMBER);

        indexInfoVo.setCarousels(carousels);

        indexInfoVo.setHotGoodses(hotGoodses);

        indexInfoVo.setNewGoodses(newGoodees);

        indexInfoVo.setRecommendGoodses(recommendGoodses);

      return ResultGenerator.genSuccessResult(indexInfoVo);
    }

}
