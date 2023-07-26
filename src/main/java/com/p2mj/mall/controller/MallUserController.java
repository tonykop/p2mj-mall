package com.p2mj.mall.controller;

import com.p2mj.mall.common.Constants;
import com.p2mj.mall.controller.param.MallUserLoginParam;
import com.p2mj.mall.service.MallUserService;
import com.p2mj.mall.util.Result;
import com.p2mj.mall.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(value = "v1",tags = "MJ商城用户操作相关接口")
@RequestMapping("/api/v1")
public class MallUserController {
    @Resource
    private MallUserService mallUserService;

    private static final Logger logger =  LoggerFactory.getLogger(MallUserController.class);

    @PostMapping("/user/login")
    @ApiOperation(value = "登录接口", notes = "返回token")
    public Result<String> login(@RequestBody @Valid MallUserLoginParam mallUserLoginParam) {

        String loginResult = mallUserService.login(mallUserLoginParam.getLoginName(), mallUserLoginParam.getPasswordMD5());
        logger.info("login api,loginName={},loginResult={}",mallUserLoginParam.getLoginName(),loginResult);
        //登录成功
        if (!StringUtils.isEmpty(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {

            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }

        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }


}
