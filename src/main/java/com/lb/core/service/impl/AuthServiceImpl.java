package com.lb.core.service.impl;

import com.lb.core.info.entity.User;
import com.lb.core.info.mapper.UserMapper;
import com.lb.core.service.AuthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author LB
 * @date 2017/8/3 13:48
 */
@Service
public class AuthServiceImpl implements AuthService {
    private Logger logger=Logger.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username,String password) {
        return userMapper.login(username,password);
    }
}
