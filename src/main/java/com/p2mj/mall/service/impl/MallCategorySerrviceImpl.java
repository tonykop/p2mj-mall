package com.p2mj.mall.service.impl;

import com.p2mj.mall.api.vo.MjMallIndexCategoryVo;
import com.p2mj.mall.api.vo.SecondLevelCategoryVO;
import com.p2mj.mall.api.vo.ThirdLevelCategoryVO;
import com.p2mj.mall.common.Constants;
import com.p2mj.mall.common.MjMallCategoryLevelEnum;
import com.p2mj.mall.dao.GoodsCategoryMapper;
import com.p2mj.mall.entity.GoodsCategory;
import com.p2mj.mall.service.MallCategoryService;
import com.p2mj.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class MallCategorySerrviceImpl implements MallCategoryService {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public List<MjMallIndexCategoryVo> getCaategoriesForIndex() {
        List<MjMallIndexCategoryVo> mjMallIndexCategoryVos = new ArrayList<MjMallIndexCategoryVo>();
        //获取一级分类的固定数量的数据
        List<GoodsCategory> firstLevelCategories = goodsCategoryMapper.seleteByLevelAndParentIdsAndNumber(Collections.singletonList(0L), MjMallCategoryLevelEnum.LEVEL_ONE.getLevel(), Constants.INDEX_CATEGORY_NUMBER);

        if(!CollectionUtils.isEmpty(firstLevelCategories)){
           List<Long> firstLevelCategoryIds = firstLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
        //获取二级分类的数据
            List<GoodsCategory> secondLevelCategories = goodsCategoryMapper.seleteByLevelAndParentIdsAndNumber(firstLevelCategoryIds, MjMallCategoryLevelEnum.LEVEL_TWO.getLevel(), 0);
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                List<Long> secondLevelCategoryIds = secondLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
                //获取三级分类的数据

                List<GoodsCategory> thirdLevelCategories = goodsCategoryMapper.seleteByLevelAndParentIdsAndNumber(secondLevelCategoryIds, MjMallCategoryLevelEnum.LEVEL_THREE.getLevel(), 0);
                if (!CollectionUtils.isEmpty(thirdLevelCategories)) {
                    //根据 parentId 将 thirdLevelCategories 分组
                    Map<Long, List<GoodsCategory>> thirdLevelCategoryMap = thirdLevelCategories.stream().collect(groupingBy(GoodsCategory::getParentId));
                    List<SecondLevelCategoryVO> secondLevelCategoryVOS = new ArrayList<>();
                    //处理二级分类
                    for (GoodsCategory secondLevelCategory : secondLevelCategories) {
                        SecondLevelCategoryVO secondLevelCategoryVO = new SecondLevelCategoryVO();
                        BeanUtil.copyProperties(secondLevelCategory, secondLevelCategoryVO);
                        //如果该二级分类下有数据则放入 secondLevelCategoryVOS 对象中
                        if (thirdLevelCategoryMap.containsKey(secondLevelCategory.getCategoryId())) {
                            //根据二级分类的id取出thirdLevelCategoryMap分组中的三级分类list
                            List<GoodsCategory> tempGoodsCategories = thirdLevelCategoryMap.get(secondLevelCategory.getCategoryId());
                            secondLevelCategoryVO.setThirdLevelCategoryVOS((BeanUtil.copyList(tempGoodsCategories, ThirdLevelCategoryVO.class)));
                            secondLevelCategoryVOS.add(secondLevelCategoryVO);
                        }
                    }
                    //处理一级分类

                    if (!CollectionUtils.isEmpty(secondLevelCategoryVOS)) {
                        //根据 parentId 将 thirdLevelCategories 分组
                        Map<Long, List<SecondLevelCategoryVO>> secondLevelCategoryVOMap = secondLevelCategoryVOS.stream().collect(groupingBy(SecondLevelCategoryVO::getParentId));
                        for (GoodsCategory firstCategory : firstLevelCategories) {
                            MjMallIndexCategoryVo mjMallIndexCategoryVO = new MjMallIndexCategoryVo();
                            BeanUtil.copyProperties(firstCategory, mjMallIndexCategoryVO);
                            //如果该一级分类下有数据则放入 newBeeMallIndexCategoryVOS 对象中
                            if (secondLevelCategoryVOMap.containsKey(firstCategory.getCategoryId())) {
                                //根据一级分类的id取出secondLevelCategoryVOMap分组中的二级级分类list
                                List<SecondLevelCategoryVO> tempGoodsCategories = secondLevelCategoryVOMap.get(firstCategory.getCategoryId());
                                mjMallIndexCategoryVO.setSecondLevelCategoryVOS(tempGoodsCategories);
                                mjMallIndexCategoryVos.add(mjMallIndexCategoryVO);
                            }
                        }
                    }



                }

            }

            return mjMallIndexCategoryVos;


        }else{
            return null;
        }

    }
}
