package com.p2mj.mall.dao;

import com.p2mj.mall.entity.IndexConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexConfigMapper {

    List<IndexConfig> findIndexConfigByTypeAndNum(@Param("configType") int configType,@Param("number") int number);

}
