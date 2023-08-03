package com.p2mj.mall.service.impl;

import com.p2mj.mall.api.vo.MjMallIndexConfigGoodsVO;
import com.p2mj.mall.controller.MallUserController;
import com.p2mj.mall.dao.IndexConfigMapper;
import com.p2mj.mall.dao.MjMallGoodsMapper;
import com.p2mj.mall.entity.IndexConfig;
import com.p2mj.mall.entity.MjMallGoods;
import com.p2mj.mall.service.MallIndexConfigService;
import com.p2mj.mall.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MallIndexConfigServiceImpl implements MallIndexConfigService {
    private static final Logger logger =  LoggerFactory.getLogger(MallUserController.class);

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Autowired
    private MjMallGoodsMapper mjMallGoodsMapper;

    @Override
    public List<MjMallIndexConfigGoodsVO> getConfigGoodsForIndex(int configType, int number) {

        List<MjMallIndexConfigGoodsVO> mjMallIndexConfigGoodsVOS = new ArrayList<>(number);
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigByTypeAndNum(configType,number);

        if(!CollectionUtils.isEmpty(indexConfigs)){
            //取出所有的goodsId
            List<Long> goodsIds = indexConfigs.stream().map(IndexConfig::getConfigId).collect(Collectors.toList());
            for(Long i:goodsIds){

                System.out.println("；");
            }

            List<MjMallGoods> mjMallGoods = mjMallGoodsMapper.selectByPrimaryKeys(goodsIds);

            mjMallIndexConfigGoodsVOS = BeanUtil.copyList(mjMallGoods,MjMallIndexConfigGoodsVO.class);
          for(MjMallIndexConfigGoodsVO mjMallIndexConfigGoodsVO:mjMallIndexConfigGoodsVOS){
              String goodsName = mjMallIndexConfigGoodsVO.getGoodsName();
              //字符串过长导致文字超出的问题
              if(goodsName.length() > 30){
                  goodsName = goodsName.substring(0,30) +"...";
                  mjMallIndexConfigGoodsVO.setGoodsName(goodsName);
              }
          }


        }

        return mjMallIndexConfigGoodsVOS;
    }
}
