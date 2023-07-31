package com.p2mj.mall.dao;

import com.p2mj.mall.entity.MallUser;
import com.p2mj.mall.entity.MallUserUpdateParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MallUserMapper {

    MallUser selectByLoginNameAndPasswd(@Param("loginName") String loginName,@Param("password") String password);

    MallUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(@Param("mallUserUpdateParam") MallUserUpdateParam mallUserUpdateParam,@Param("userId") String userId);

}
