package com.p2mj.mall.config.handler;

import com.p2mj.mall.common.Constants;
import com.p2mj.mall.common.MjMallException;
import com.p2mj.mall.common.ServiceResultEnum;
import com.p2mj.mall.config.annotation.TokenToMallUser;
import com.p2mj.mall.dao.MallUserMapper;
import com.p2mj.mall.dao.MallUserTokenMapper;
import com.p2mj.mall.entity.MallUser;
import com.p2mj.mall.entity.MallUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 执行逻辑总结如下。(1)获取请求头中的token值，如果不存在，则返回错误信息给前端；如果存在，则继续后续流程。
 * (2)通过token值来查询MallUserToken对象，查看是否存在或是否过期，如果不存在或已过期，则返回错误信息给前端；如果正常，则继续后续流程。
 * (3)通过MallUserToken对象中的userId字段查询MallUser用户对象，判断是否存在和是否已被封禁，如果用户状态正常，则返回用户对象供对应的方法使用，否则返回错误信息。
 */
@Component
public class TokenToMallUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MallUserMapper mallUserMapper;
    @Autowired
    private MallUserTokenMapper mallUserTokenMapper;

    public TokenToMallUserMethodArgumentResolver(){

    }



    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        if(parameter.hasParameterAnnotation(TokenToMallUser.class)){
            return true;
        }

        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        if(parameter.getParameterAnnotation(TokenToMallUser.class) instanceof TokenToMallUser){
            MallUser mallUser = null;
            //获取请求头中的headers
            String token = webRequest.getHeader("token");
            //验证token值是否存在
            if(null != token && !"".equals(token) && token.length() == Constants.TOKEN_LENGTH){
               //通过token值查询用户对象
                MallUserToken mallUserToken = mallUserTokenMapper.selectByToken(token);
                if(mallUserToken == null || mallUserToken.getExpireTime().getTime()<=System.currentTimeMillis()){
                    MjMallException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }

                mallUser = mallUserMapper.selectByPrimaryKey(mallUserToken.getUserId());
                //用户不存在
                if(mallUser == null){
                    MjMallException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }

                //是否封禁
                if(mallUser.getLockedFlag().intValue() == 1){
                    MjMallException.fail(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
                }
                //返回用户对象供外对应的方法使用
                return mallUser;
            }else {
                MjMallException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }

        return "用户未登录！";
    }
}
