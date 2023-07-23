package com.p2mj.mall.dao;

import com.p2mj.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /**
     * f返回数据列表
     */
    List<User> findAllUsers();

    /**
     * 添加User
     * @param user
     */
    int insertUser(User user);

    /**
     * 修改
     * @param user
     */

    int updateUser(User user);

    /**
     * 删除
     * @param id
     * @return
     */

    int delUser(Integer id);
}
