package com.p2mj.mall.dao;

import com.p2mj.mall.entity.MallShoppingCartItem;
import com.p2mj.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallShoppingCartItemMapper {

    /**
     * 删除一条记录
     * @param cartItemId
     * @return
     */
    int deleteByPrimaryKey(Long cartItemId);

    /**
     * 保存一条新纪录
     * @param record
     * @return
     */
       int insertSelective(MallShoppingCartItem record);

    /**
     * 根据主键查询记录
     * @param cartItemId
     * @return
     */
    MallShoppingCartItem selectByPrimaryKey(Long cartItemId);

    /**
     * 根据userId和goodsId查询记录
     * @param mjMallUserId
     * @param goodsId
     * @return
     */
    MallShoppingCartItem selectByUserIdAndGoodsId(@Param("mjMallUserId") Long mjMallUserId, @Param("goodsId") Long goodsId);

    /**
     * 根据userId和number字段获取固定数量的购物项列表数据
     * @param mjMallUserId
     * @param number
     * @return
     */
    List<MallShoppingCartItem> selectByUserId(@Param("mjMallUserId") Long mjMallUserId, @Param("number") int number);

    /**
     * 根据userId和购物项id列表获取购物项列表数据
     * @param mjMallUserId
     * @param cartItemIds
     * @return
     */
    List<MallShoppingCartItem> selectByUserIdAndCartItemIds(@Param("mjMallUserId") Long mjMallUserId, @Param("cartItemIds") List<Long> cartItemIds);

    /**
     * 根据userId查询当前用户已添加了多少条记录
     * @param mjMallUserId
     * @return
     */
    int selectCountByUserId(Long mjMallUserId);

    /**
     * 修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MallShoppingCartItem record);

    /**
     * 购物项列表查询，使用分页功能
     * @param pageQueryUtil
     * @return
     */
    List<MallShoppingCartItem> findMallCartItems(PageQueryUtil pageQueryUtil);

    /**
     * 购物列表拆查询，使用分页功能
     * @param pageQueryUtil
     * @return
     */
    int getTotalMallCartItems(PageQueryUtil pageQueryUtil);

}
