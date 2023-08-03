package com.p2mj.mall.dao;

import com.p2mj.mall.entity.MjMallGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MjMallGoodsMapper {

    List<MjMallGoods> selectByPrimaryKeys(@Param("goodsIds") List<Long> goodsIds);
}
