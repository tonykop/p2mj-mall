package com.p2mj.mall.dao;

import com.p2mj.mall.entity.GoodsCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsCategoryMapper {

    List<GoodsCategory> seleteByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds,@Param("categoryLevel") int categoryLevel,@Param("number") int number);
}
