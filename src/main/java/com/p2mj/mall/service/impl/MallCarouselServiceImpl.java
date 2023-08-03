package com.p2mj.mall.service.impl;

import com.p2mj.mall.api.vo.MjMallIndexCarouselVo;
import com.p2mj.mall.dao.CarouserlMapper;
import com.p2mj.mall.entity.Carousel;
import com.p2mj.mall.service.MallCarouselService;
import com.p2mj.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallCarouselServiceImpl implements MallCarouselService {
    @Autowired
    private CarouserlMapper carouserlMapper;
    @Override
    public List<MjMallIndexCarouselVo> getCarouselsForIndex(int number) {
       List<MjMallIndexCarouselVo> mjMallIndexCarouselVos = new ArrayList<>(number);
       List<Carousel> carousels = carouserlMapper.findCarouselByNum(number);
       if(!CollectionUtils.isEmpty(carousels)){
                mjMallIndexCarouselVos = BeanUtil.copyList(carousels,MjMallIndexCarouselVo.class);
       }

        return mjMallIndexCarouselVos;
    }
}
