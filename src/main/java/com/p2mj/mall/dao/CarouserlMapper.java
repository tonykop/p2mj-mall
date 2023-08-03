package com.p2mj.mall.dao;

import com.p2mj.mall.entity.Carousel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarouserlMapper {
    List<Carousel> findCarouselByNum(@Param("number") int number);
}
