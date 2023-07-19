package com.p2mj.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/tets/type/conversion")
    public void typeConversionTest(String goodsName, float weight, int type,Boolean onSale){
        System.out.println("goodsName:" + goodsName);
        System.out.println("weight:" + weight);
        System.out.println("type:" + type);
        System.out.println("onSale:" + onSale);



    }

}
