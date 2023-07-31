package com.p2mj.mall.config;


import com.p2mj.mall.entity.MallUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api(){

        RequestParameterBuilder tokenParam = new RequestParameterBuilder();
        //ParameterBuilder tokenParam = new ParameterBuilder();
        List<RequestParameter> swaggerParams = new ArrayList<>();
        tokenParam.name("token").description("用户认证信息")
                .in(ParameterType.HEADER)
                .required(false).build();
        swaggerParams.add(tokenParam.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(MallUser.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.p2mj.mall.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(swaggerParams);
                //.globalOperationParameters(swaggerParams);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("MJ商城接口文档")
                .description("swagger 文档  by lqj")
                .version("1.0")
                .build();
    }





}
