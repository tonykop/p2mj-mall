package com.p2mj.mall.service;

import com.p2mj.mall.api.vo.MallShoppingCartItemVO;
import com.p2mj.mall.controller.param.SaveCartItemParam;
import com.p2mj.mall.entity.MallShoppingCartItem;

import java.util.List;

public interface MallShoppingCartService {
    /**
     * 保存商品到购物车中
     * @param saveCartItemParam
     * @return
     */
    String savaMallCartItem(SaveCartItemParam saveCartItemParam,Long UserId);

    /**
     * 获取我的购物车中的列表数据
     * @param mallUserId
     * @return
     */
    List<MallShoppingCartItemVO> getMyShoppingCartItems(Long mallUserId);

    /**
     *
     * @param mallShoppingCartItem
     * @return
     */
    String updateMallCartItem(MallShoppingCartItem mallShoppingCartItem,Long userId);

    /**
     * 删除购物车中的商品
     * @param mallShoppingCartItemId
     * @return
     */
    Boolean deleteById(Long mallShoppingCartItemId);

    /**
     * 获取购物项详情
     * @param mallShoppingCartItemId
     * @return
     */

    MallShoppingCartItem getNewBeeMallCartItemById(Long mallShoppingCartItemId);


}
