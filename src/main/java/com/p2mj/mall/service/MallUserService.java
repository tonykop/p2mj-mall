package com.p2mj.mall.service;

import com.p2mj.mall.entity.MallUserUpdateParam;

public interface MallUserService {
    /**
     * 用户登录
     * @param  loginName
     * @param  passwordMD5
     * @return
     * @author tonyKop
     * @createTime at 2023/7/25
     */

    String login(String loginName, String passwordMD5);

    Boolean updateUserInfo(MallUserUpdateParam mallUserUpdateParam,String userId);

    Boolean logout(Long userId);
}
