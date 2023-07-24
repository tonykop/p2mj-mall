package com.p2mj.mall.dao;

import com.p2mj.mall.entity.MallUser;
import com.p2mj.mall.entity.MallUserToken;

public interface MallUserTokenMapper {

    int insertSelective(MallUserToken record);

    MallUserToken selectByPrimaryKey(Long userId);

    MallUserToken selectByToken(String token);

    int updatePrimaryKeySelective(MallUserToken record);
}
