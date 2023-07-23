package com.p2mj.mall.controller;

import com.p2mj.mall.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TestSwaggerController {
    static Map<Integer, User> userMap = Collections.synchronizedMap(new HashMap<Integer, User>());
    //初始化
    static {
        User user= new User();
        user.setId(1);
        user.setName("p2mjMall");
        user.setPassword("1111111111");
        User user2= new User();
        user2.setId(2);
        user2.setName("p2mjMall2");
        user2.setPassword("22222222222");
        userMap.put(1,user);
        userMap.put(2,user2);
    }

    @ApiOperation(value = "获取用户列表",notes = "")
    @GetMapping("/users")
    public List<User> getUserList(){
        List<User> users = new ArrayList<User>(userMap.values());
        return users;
    }

    @ApiOperation(value = "新增用户",notes ="根据User对象新增用户")
    @ApiImplicitParam(name="user",value = "用户实体",required = true,dataType = "User")
    @PostMapping("/users")
    public String postUser(@RequestBody User user){
        userMap.put(user.getId(),user);
        return "新增成功";
    }

    @ApiOperation(value = "获取用户信息",notes = "根据id获取用户详细信息")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "int")
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id)
    {
    return userMap.get(id);
    }

    @ApiOperation(value="更新用户详细信息",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "用户id",required = true,dataType = "int"),
            @ApiImplicitParam(name = "user",value = "用户实体user",required = true,dataType = "User")
    })
    @PutMapping("/users/{id}")
    public String putUser(@PathVariable Integer id,@RequestBody User user){
        User tempUser = userMap.get(id);
        tempUser.setName(user.getName());
        tempUser.setPassword(user.getPassword());
        userMap.put(id,tempUser);
        return "更新成功";
    }
    @ApiOperation(value = "删除用户",notes = "根据id删除用户")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "int")
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Integer id){
        userMap.remove(id);
        return "删除成功";
    }

}
