package com.p2mj.mall.service.impl;

import com.p2mj.mall.api.vo.MallShoppingCartItemVO;
import com.p2mj.mall.common.Constants;
import com.p2mj.mall.common.MjMallException;
import com.p2mj.mall.common.ServiceResultEnum;
import com.p2mj.mall.controller.param.SaveCartItemParam;
import com.p2mj.mall.dao.MallShoppingCartItemMapper;
import com.p2mj.mall.dao.MjMallGoodsMapper;
import com.p2mj.mall.entity.MallShoppingCartItem;
import com.p2mj.mall.entity.MjMallGoods;
import com.p2mj.mall.service.MallShoppingCartService;
import com.p2mj.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MallShoppingCartServiceImpl implements MallShoppingCartService {

    @Autowired
    MallShoppingCartItemMapper mallShoppingCartItemMapper;

    @Autowired
    MjMallGoodsMapper mjMallGoodsMapper;

    @Override
    public String savaMallCartItem(SaveCartItemParam saveCartItemParam,Long userId) {
       MallShoppingCartItem temp = mallShoppingCartItemMapper.selectByUserIdAndGoodsId(userId,saveCartItemParam.getGoodsId());
       if(temp != null){
           //如果已存在，则修改该记录
           MjMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
       }

        MjMallGoods mjMallGoods = mjMallGoodsMapper.selectByPrimarykey(saveCartItemParam.getGoodsId());
       //商品为空
        if(mjMallGoods == null){
            return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
        }

        int totalItem = mallShoppingCartItemMapper.selectCountByUserId(userId);
        //超出单个商品的最大数量
        if(saveCartItemParam.getGoodsCount() < 1){
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //超出单个商品的最大数量
        if(saveCartItemParam.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER){
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }

        //超出最大数量
        if(totalItem > Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER){
            return ServiceResultEnum.SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR.getResult();
        }

        MallShoppingCartItem mallShoppingCartItem = new MallShoppingCartItem();
        BeanUtil.copyProperties(saveCartItemParam,mallShoppingCartItem);
        mallShoppingCartItem.setUserId(userId);
        //保存记录
        if(mallShoppingCartItemMapper.insertSelective(mallShoppingCartItem)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public List<MallShoppingCartItemVO> getMyShoppingCartItems(Long mallUserId) {
       List<MallShoppingCartItemVO> mallShoppingCartItemVOS = new ArrayList<>();
       List<MallShoppingCartItem> mallShoppingCartItems = mallShoppingCartItemMapper.selectByUserId(mallUserId,Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER);


        return getMallShoppingCartItemVOS(mallShoppingCartItemVOS,mallShoppingCartItems);
    }

    @Override
    public String updateMallCartItem(MallShoppingCartItem mallShoppingCartItemParam,Long userId) {
        MallShoppingCartItem mallShoppingCartItem = mallShoppingCartItemMapper.selectByPrimaryKey(mallShoppingCartItemParam.getCartItemId());
        if(mallShoppingCartItem == null){
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        if(!mallShoppingCartItem.getUserId().equals(userId)){
           MjMallException.fail(ServiceResultEnum.NO_PERMISSION_ERROR.getResult());
        }
        //超出单个商品的最大数
        if(mallShoppingCartItemParam.getGoodsCount()>Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER){
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }

        mallShoppingCartItem.setGoodsCount(mallShoppingCartItemParam.getGoodsCount());
        mallShoppingCartItem.setCreateTime(new Date());
        //修改记录
        if(mallShoppingCartItemMapper.updateByPrimaryKeySelective(mallShoppingCartItem)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Boolean deleteById(Long mallShoppingCartItemId) {
        return mallShoppingCartItemMapper.deleteByPrimaryKey(mallShoppingCartItemId)>0;
    }

    @Override
    public MallShoppingCartItem getNewBeeMallCartItemById(Long mallShoppingCartItemId) {
        return mallShoppingCartItemMapper.selectByPrimaryKey(mallShoppingCartItemId);
    }


    private List<MallShoppingCartItemVO> getMallShoppingCartItemVOS(List<MallShoppingCartItemVO> mallShoppingCartItemVOS,List<MallShoppingCartItem> mallShoppingCartItems){
        if(!CollectionUtils.isEmpty(mallShoppingCartItems)){
            //查询商品信息并做数据转换
            List<Long>  mallGoodsIds = mallShoppingCartItems.stream().map(MallShoppingCartItem::getGoodsId).collect(Collectors.toList());
            List<MjMallGoods> mjMallGoods = mjMallGoodsMapper.selectByPrimaryKeys(mallGoodsIds);
            Map<Long,MjMallGoods> mjMallGoodsMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(mjMallGoods)){
                mjMallGoodsMap = mjMallGoods.stream().collect(Collectors.toMap(MjMallGoods::getGoodsId, Function.identity(),(entity1,entity2)->entity1));
            }
            for(MallShoppingCartItem mallShoppingCartItem:mallShoppingCartItems){
                MallShoppingCartItemVO mallShoppingCartItemVO = new MallShoppingCartItemVO();
                BeanUtil.copyProperties(mallShoppingCartItem,mallShoppingCartItemVO);
                if(mjMallGoodsMap.containsKey(mallShoppingCartItem.getGoodsId())){
                   MjMallGoods mjMallGoodsTemp = mjMallGoodsMap.get(mallShoppingCartItem.getGoodsId());
                   mallShoppingCartItemVO.setGoodsCoverImg(mjMallGoodsTemp.getGoodsCoverImg());
                   String goodsName = mjMallGoodsTemp.getGoodsName();
                   //字符串过长导致文字超出
                    if(goodsName.length()>28){
                        goodsName = goodsName.substring(0,28)+"...";
                    }

                    mallShoppingCartItemVO.setGoodsName(goodsName);
                    mallShoppingCartItemVO.setSellingPrice(mjMallGoodsTemp.getSellingPrice());
                    mallShoppingCartItemVOS.add(mallShoppingCartItemVO);
                }
            }
        }
        return mallShoppingCartItemVOS;
    }


}
