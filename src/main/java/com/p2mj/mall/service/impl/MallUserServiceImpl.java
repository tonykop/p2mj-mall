package com.p2mj.mall.service.impl;

import com.p2mj.mall.common.ServiceResultEnum;
import com.p2mj.mall.dao.MallUserMapper;
import com.p2mj.mall.dao.MallUserTokenMapper;
import com.p2mj.mall.entity.MallUser;
import com.p2mj.mall.entity.MallUserToken;
import com.p2mj.mall.entity.MallUserUpdateParam;
import com.p2mj.mall.service.MallUserService;
import com.p2mj.mall.util.NumberUtil;
import com.p2mj.mall.util.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MallUserServiceImpl implements MallUserService {
    @Autowired
    private MallUserMapper mallUserMapper;

    @Autowired
    private MallUserTokenMapper mallUserTokenMapper;

    /**
     *
     * @param  loginName
     * @param  passwordMD5
     * @return
     */
    /**
     * 结合前文中的登录流程图来理解，用户登录的详细过程如下。
     * (1)根据用户名称和密码查询用户数据，如果存在，则继续后续流程。
     * (2)判断用户状态是否正常，如果一切正常，则继续后续流程。
     * (3)生成token值，这里可以简单地将其理解为生成一个随机字符串，在这一步其实已经完成了登录逻辑，只是后续需要对token值进行查询，所以还需要将用户的token信息入库。
     * (4)根据用户id查询token信息表，以此结果来决定是进行token更新操作，还是进行新增操作。
     * (5)根据当前时间获取token过期时间。
     * (6)封装用户token信息并进行入库操作（新增或修改）。
     * (7)返回token值。
     *
     */
    @Override
    public String login(String loginName, String passwordMD5) {
        MallUser user = mallUserMapper.selectByLoginNameAndPasswd(loginName,passwordMD5);
        if(user != null){
            if(user.getLockedFlag() ==  1){
                return ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult();
            }

            //登录后即执行修改token的操作
            //获取一个新token
            String token = getToken(System.currentTimeMillis()+"",user.getUserId());
           //获取该用户是否存在token
            MallUserToken mallUserToken = mallUserTokenMapper.selectByPrimaryKey(user.getUserId());
            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime()+2*24*3600*1000);//过期时间48小时
            if(mallUserToken == null){
                mallUserToken = new MallUserToken();
                mallUserToken.setUserId(user.getUserId());
                mallUserToken.setToken(token);
                mallUserToken.setUpdateTime(now);
                mallUserToken.setExpireTime(expireTime);
                //新增一条token数据
                if(mallUserTokenMapper.insertSelective(mallUserToken)>0){
                    //新增一条token数据
                    return token;
                }
            }else{
                mallUserToken.setToken(token);
                mallUserToken.setUpdateTime(now);
                mallUserToken.setExpireTime(expireTime);
                //更新
                if(mallUserTokenMapper.updatePrimaryKeySelective(mallUserToken)>0){
                    //修改成功后返回
                    return token;
                }
            }

        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    /**
     * 生成token值
     *
     * @param timeStr
     * @Param userId
     * @return
     */

    private String getToken(String timeStr,Long userId){
        String src = timeStr + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }

    @Override
    public Boolean updateUserInfo(MallUserUpdateParam mallUserUpdateParam, String userId) {

        int flag = mallUserMapper.updateByPrimaryKeySelective(mallUserUpdateParam,userId);
        if(flag > 0){
            return true;
        }

        return false;
    }

    @Override
    public Boolean logout(Long userId) {

        return mallUserTokenMapper.logout(userId) >=1;
    }


}
