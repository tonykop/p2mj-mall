package com.p2mj.mall.dao;

import com.p2mj.mall.entity.MjMallGoods;
import com.p2mj.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MjMallGoodsMapper {

    List<MjMallGoods> selectByPrimaryKeys(@Param("goodsIds") List<Long> goodsIds);

    /**
     * create at 20230812
     * 第13章内容
     * 根据搜索字段查询分页数据
     * @param pageQueryUtil
     * @return
     */
    List<MjMallGoods> findMjMallGoodsListBySearch(PageQueryUtil pageQueryUtil);

    /**
     * create at 20230812
     * 第13章内容
     * 根据搜索字段查询总数
     * @param pageQueryUtil
     * @return
     */

    int getToTalMjMallGoodsBySearch(PageQueryUtil pageQueryUtil);

    /**
     * create at 20230812
     * 第13章内容
     * 根据商品Id获取商品详细
     * @param goodsId
     * @return
     */
    MjMallGoods selectByPrimarykey(Long goodsId);
}
