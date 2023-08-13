package com.p2mj.mall.service.impl;

import com.p2mj.mall.api.vo.MjMallSearchGoodsVO;
import com.p2mj.mall.dao.MjMallGoodsMapper;
import com.p2mj.mall.entity.MjMallGoods;
import com.p2mj.mall.service.MallGoodsService;
import com.p2mj.mall.util.BeanUtil;
import com.p2mj.mall.util.PageQueryUtil;
import com.p2mj.mall.util.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MallGoodsServiceImpl  implements MallGoodsService {
    private static final Logger logger = LoggerFactory.getLogger(MallGoodsServiceImpl.class);


    @Autowired
    private MjMallGoodsMapper mjMallGoodsMapper;

    @Override
    public PageResult searchMjMallGoods(PageQueryUtil pageQueryUtil) {

        List<MjMallGoods> goodsList = mjMallGoodsMapper.findMjMallGoodsListBySearch(pageQueryUtil);

        int total = mjMallGoodsMapper.getToTalMjMallGoodsBySearch(pageQueryUtil);
        logger.info("====================>total:"+total);
        List<MjMallSearchGoodsVO> mjMallSearchGoodsVOS = new ArrayList<>();

        if(!CollectionUtils.isEmpty(goodsList)){

            mjMallSearchGoodsVOS = BeanUtil.copyList(goodsList,MjMallSearchGoodsVO.class);
            for(MjMallSearchGoodsVO mjMallSearchGoodsVO:mjMallSearchGoodsVOS){
                String goodsName = mjMallSearchGoodsVO.getGoodsName();
                String goodsIntro = mjMallSearchGoodsVO.getGoodsIntro();
                //字符串过长会导致文字超出范围
                if(goodsName.length() > 28){
                    goodsName = goodsName.substring(0,28) +"";
                    mjMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if(goodsIntro.length() > 30){
                    goodsIntro = goodsIntro.substring(0,30)+"";
                    mjMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }

        }
        PageResult pageResult = new PageResult(mjMallSearchGoodsVOS,total,pageQueryUtil.getLimit(),pageQueryUtil.getPage());
//        List<MjMallSearchGoodsVO> mjMallSearchGoodsVO = (List<MjMallSearchGoodsVO>) pageResult.getList();
//        for(MjMallSearchGoodsVO mjMallGoods:mjMallSearchGoodsVO){
//            logger.info("#######:{},{},{}",pageResult.getCurrPage(),pageResult.getPageSize(),pageResult.getTotalCount());
//            logger.info("=========》goodsName:{}",mjMallGoods.getGoodsName());
//        }

        return pageResult;
    }

    @Override
    public MjMallGoods getMjMallGoodsById(Long id) {
        return  mjMallGoodsMapper.selectByPrimarykey(id);
    }
}
