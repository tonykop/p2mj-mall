package com.p2mj.mall.controller;

import com.p2mj.mall.common.Constants;
import com.p2mj.mall.config.annotation.TokenToMallUser;
import com.p2mj.mall.controller.param.MallUserLoginParam;
import com.p2mj.mall.entity.MallUser;
import com.p2mj.mall.entity.MallUserUpdateParam;
import com.p2mj.mall.service.MallUserService;
import com.p2mj.mall.util.Result;
import com.p2mj.mall.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/test1")
    @ApiOperation(value = "测试接口",notes = "方法中含有@TokenToMallUser注释")
    public Result<String> test1(@TokenToMallUser MallUser user){
        //此接口含有@TokenToMallUser注解，即需要登录验证的接口
        Result result = null;
        if(user == null){
            //如果通过请求header中的token未查询到用户即token无效，则登录验证失败，返回未登录错误码
            result = ResultGenerator.genErrorResult(416,"未登录");
            return result;
        }else{
            //登录验证通过
            result= ResultGenerator.genSuccessResult("登录验证通过");
        }

        return result;
    }
    @GetMapping(value = "/test2")
    @ApiOperation(value = "测试接口",notes = "方法中无@TokenToMallUser注释")
    public Result<String> test2(){
        //此接口不含@TokenToMallUser注解，即访问此接口无须登录验证，此类接口在实际开发中应该很少，为了安全起见，所有接口都应该做登录验证
        Result result = ResultGenerator.genSuccessResult("此接口无需登录验证，请求成功");
        //直接返回业务逻辑返回的数据即可
        return  result;
    }


    @GetMapping("/user/info")
    @ApiOperation(value = "获取用户信息",notes = "")
    public Result<MallUser> getUserDetail(@TokenToMallUser MallUser loginMallUser){
        return ResultGenerator.genSuccessResult(loginMallUser);
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "修改用户信息",notes = "")
    public Result updateInfo(@RequestBody @ApiParam("用户信息") MallUserUpdateParam mallUserUpdateParam,@TokenToMallUser MallUser loginMallUser){
        Boolean flag = mallUserService.updateUserInfo(mallUserUpdateParam,loginMallUser.getUserId().toString());
        if(flag){
            //返回成功
            Result result = ResultGenerator.genSuccessResult();
            return result;
        }else {
            //返回失败
            Result result = ResultGenerator.genFailResult("修改用户信息失败");
            return  result;
        }
    }

    @PostMapping("/user/logout")
    @ApiOperation(value = "登出接口",notes = "清除token")
    public Result<String> logout(@TokenToMallUser MallUser loginMallUser){

        Boolean flage = mallUserService.logout(loginMallUser.getUserId());
        logger.info("用户注销 api,loginMallUser={}",loginMallUser.getUserId());
        //注销成功
        if(flage){
            return ResultGenerator.genSuccessResult();
        }

        //注销失败
        return ResultGenerator.genFailResult("注销失败");

    }

}
