package com.lb.core.info.mapper;

import com.lb.core.info.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User login(@Param(value = "username") String username, @Param(value ="password") String password);
}