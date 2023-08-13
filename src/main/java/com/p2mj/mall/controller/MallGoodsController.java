package com.p2mj.mall.controller;

import com.p2mj.mall.api.vo.MjMallGoodsDetailVO;
import com.p2mj.mall.api.vo.MjMallSearchGoodsVO;
import com.p2mj.mall.common.Constants;
import com.p2mj.mall.common.MjMallException;
import com.p2mj.mall.common.ServiceResultEnum;
import com.p2mj.mall.config.annotation.TokenToMallUser;
import com.p2mj.mall.entity.MallUser;
import com.p2mj.mall.entity.MjMallGoods;
import com.p2mj.mall.service.MallGoodsService;
import com.p2mj.mall.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "v1",tags = "Mjs商城商品搜索接口")
@RequestMapping("/api/v1")
public class MallGoodsController {
    private static final Logger logger = LoggerFactory.getLogger(MallGoodsController.class);

    @Resource
    private MallGoodsService mallGoodsService;

    @GetMapping("/search")
    @ApiOperation(value = "商品搜索接口",notes ="根据关键字和分类Id进行搜索" )
    public Result<PageResult<List<MjMallSearchGoodsVO>>> search(@RequestParam(required = false) @ApiParam(value = "搜索关键字") String keyword, @RequestParam(
            required = false) @ApiParam(value = "分类Id") Long goodsCategoryId, @RequestParam(
                    required = false) @ApiParam(value = "orderBy") String orderBy, @RequestParam(
                            required = false) @ApiParam(value = "页码") Integer pageNumber, @TokenToMallUser MallUser loginMallUser
    ) {

        logger.info("goods serach api,keyword={},goodsCategoryId={}," +
                "orederBy={},pageNumber={},userId={}",keyword,goodsCategoryId,orderBy,pageNumber,loginMallUser.getUserId());

        Map params = new HashMap(4);
        //两个搜索参数都为空,直接返回异常
        if((goodsCategoryId==null) && (keyword.length()<0)){
            MjMallException.fail("非法的搜索参数");
        }
        if(pageNumber == null || pageNumber <1){
            pageNumber = 1;
        }
        params.put("goodsCategoryId",goodsCategoryId);
        params.put("page",pageNumber);
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        //对keyword进行过滤，去掉空格
        if(!StringUtils.isEmpty(keyword)){
            params.put("keyword",keyword);
        }
        if(!StringUtils.isEmpty(orderBy)){
            params.put("orderBy",orderBy);
        }
        //搜索上架状态的商品
        params.put("goodsSellStatus",Constants.SELL_STATUS_UP);

        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
       PageResult<List<MjMallSearchGoodsVO>> pageResult = mallGoodsService.searchMjMallGoods(pageQueryUtil);
        List<MjMallSearchGoodsVO> mjMallSearchGoodsVO = (List<MjMallSearchGoodsVO>) pageResult.getList();
        for(MjMallSearchGoodsVO mjMallGoods:mjMallSearchGoodsVO){
            logger.info("#######:{},{},{}",pageResult.getCurrPage(),pageResult.getPageSize(),pageResult.getTotalCount());
            logger.info("=========》goodsName:{}",mjMallGoods.getGoodsName());
        }
         return ResultGenerator.genSuccessResult(mallGoodsService.searchMjMallGoods(pageQueryUtil));
    }

    @GetMapping("/goods/detail/{goodsId}")
    @ApiOperation(value = "商品详情接口",notes = "传参为商品ID")
    public Result<MjMallGoodsDetailVO> goodsDetail(@ApiParam(value = "商品id") @PathVariable("goodsId") Long goosId,@TokenToMallUser MallUser loginUser){
        logger.info("goods detail api,goods={},userId={}",goosId,loginUser.getUserId());
        if(goosId<0){
            return ResultGenerator.genFailResult("参数异常");
        }

        MjMallGoods mjMallGoods = mallGoodsService.getMjMallGoodsById(goosId);
        if(mjMallGoods == null){
            return ResultGenerator.genFailResult("参数异常");
        }

        if(Constants.SELL_STATUS_UP != mjMallGoods.getGoodsSellStatus()){
            MjMallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }

        MjMallGoodsDetailVO goodsDetailVO = new MjMallGoodsDetailVO();
        BeanUtil.copyProperties(mjMallGoods,goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(mjMallGoods.getGoodsCarousel().split(","));
        return ResultGenerator.genSuccessResult(goodsDetailVO);
    }
}
