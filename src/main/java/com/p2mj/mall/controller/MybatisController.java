package com.p2mj.mall.controller;

import com.p2mj.mall.dao.UserDao;
import com.p2mj.mall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MybatisController {
    @Autowired
    UserDao userDao;

    //查询所有记录
    @GetMapping("/users/mybatis/queryAll")
    public List<User> queryAll(){
        return userDao.findAllUsers();
    }
    //新增依提奥记录
    @GetMapping("/users/mybatis/insert")
    public Boolean inssert(String name,String password){
        if(StringUtils.isEmpty(name)||StringUtils.isEmpty(password)){
            return false;
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userDao.insertUser(user) > 0;
    }

    //修改一条记录
    @GetMapping("/users/mybatis/update")
    public Boolean upadte(Integer id,String name, String password){
        if(id == null || id< 1 || StringUtils.isEmpty(name)||StringUtils.isEmpty(password)) {
              return false;
        }
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        return userDao.updateUser(user) > 0;
    }
   //删除一条记录
    @GetMapping("/users/mybatis/delete")
    public  boolean delete(Integer id ){
        if(id == null || id<1){
            return false;
        }
        return userDao.delUser(id) >0;
    }
}
