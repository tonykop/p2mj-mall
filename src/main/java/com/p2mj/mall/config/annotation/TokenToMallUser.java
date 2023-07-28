package com.p2mj.mall.config.annotation;

import java.lang.annotation.*;

/**
 * 10.3.2　后端处理token及身份验证后端处理token的步骤如下。
 * (1)生成token，在第10.2节中已经做过介绍。
 * (2)获取前端请求中的token值。
 * (3)验证token值，判断是否存在、是否过期等。
 * 完成登录功能后，需要对用户的登录状态进行验证，
 * 这里所说的登录状态即“token值是否存在及token值是否有效”。
 * token值是否有效通过后端代码实现，由于大部分接口都需要进行登录验证，
 * 如果每个方法都添加查询用户数据的语句，则有些多余，因此对方法做了抽取，
 * 通过注解的形式返回用户信息，步骤如下。首先自定义@TokenToMallUser注解，
 * 使用注解和AOP方式将用户对象注入方法，代码如下：
 *
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToMallUser {
    /**
     * 当前用户在请求中的名字
     * @return
     */
    String value() default "user";
}
