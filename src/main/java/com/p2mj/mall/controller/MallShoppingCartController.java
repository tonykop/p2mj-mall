package com.p2mj.mall.controller;

import com.p2mj.mall.api.vo.MallShoppingCartItemVO;
import com.p2mj.mall.common.Constants;
import com.p2mj.mall.common.ServiceResultEnum;
import com.p2mj.mall.config.annotation.TokenToMallUser;
import com.p2mj.mall.controller.param.SaveCartItemParam;
import com.p2mj.mall.entity.MallShoppingCartItem;
import com.p2mj.mall.entity.MallUser;
import com.p2mj.mall.service.MallShoppingCartService;
import com.p2mj.mall.util.PageQueryUtil;
import com.p2mj.mall.util.PageResult;
import com.p2mj.mall.util.Result;
import com.p2mj.mall.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "v1",tags = "Mj商城购物车相关接口")
@RequestMapping("/api/v1")
public class MallShoppingCartController {
    @Resource
    MallShoppingCartService mallShoppingCartService;

    @PostMapping("/shop-cart")
    @ApiOperation(value = "添加商品到购物车接口",notes = "传参为商品id,数量")
    public Result saveMallShoppingCartItem(@RequestBody SaveCartItemParam saveCartItemParam, @TokenToMallUser MallUser loginMallUser){
        String saveResul = mallShoppingCartService.savaMallCartItem(saveCartItemParam,loginMallUser.getUserId());
        //添加成功
        if(ServiceResultEnum.SUCCESS.getResult().equals(saveResul)){
            return ResultGenerator.genSuccessResult();
        }
        //添加失败
        return ResultGenerator.genFailResult(saveResul);

    }

//    @GetMapping("/shop-cart")
//    @ApiOperation(value = "购物车列表（网页移动端不分页）",notes = "")
//    public Result<List<MallShoppingCartItemVO>> cartItemList(@TokenToMallUser MallUser loginUser){
//      return ResultGenerator.genSuccessResult(mallShoppingCartService.getMyShoppingCartItems(loginUser.getUserId()));
//    }
//
//    public Result<PageResult<List<MallShoppingCartItemVO>>> cartItemPageList(Integer pageNumber,@TokenToMallUser MallUser loginMallUser){
//        Map params = new HashMap(4);
//        if(pageNumber == null || pageNumber < 1){
//            pageNumber = 1;
//        }
//        params.put("userId",loginMallUser.getUserId());
//        params.put("page",pageNumber);
//        params.put("limit", Constants.SHOPPING_CART_PAGE_LIMIT);
//        //封装分页请求参数
//        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
//        return ResultGenerator.genSuccessResult(mallShoppingCartService.getMyShoppingCartItems(pageQueryUtil));
//    }

   @PutMapping("/shop-cart")
   @ApiOperation(value = "修改购物项数据",notes = "传参为购物项id,数量")
    public Result updateMallShoppingCartItem(@RequestBody MallShoppingCartItem updateMallShoppingCartItem,@TokenToMallUser MallUser loginMallUser){
        String updateResult = mallShoppingCartService.updateMallCartItem(updateMallShoppingCartItem,loginMallUser.getUserId());
        //修改成功
        if(ServiceResultEnum.SUCCESS.getResult().equals(updateResult)){
            return ResultGenerator.genSuccessResult();
        }
        //修改失败
        return ResultGenerator.genFailResult(updateResult);
    }

    public Result updateMallShoppingCartItem(@PathVariable("mallShoppingCartItemId") Long mallShoppingCartItemId,@TokenToMallUser MallUser loginMallUser){
        MallShoppingCartItem mallShoppingCartItem = mallShoppingCartService.getNewBeeMallCartItemById(mallShoppingCartItemId);
        if(!loginMallUser.getUserId().equals(mallShoppingCartItem.getUserId())){
            return ResultGenerator.genFailResult(ServiceResultEnum.NO_PERMISSION_ERROR.getResult());
        }

        Boolean deleteResult = mallShoppingCartService.deleteById(mallShoppingCartItemId);
        //删除成功
        if(deleteResult){
            return ResultGenerator.genSuccessResult();
        }
        //删除失败
        return ResultGenerator.genFailResult(ServiceResultEnum.OPERATE_ERROR.getResult());
    }
}
