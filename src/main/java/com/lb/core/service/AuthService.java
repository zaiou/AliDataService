package com.lb.core.service;

import com.lb.core.info.entity.User;

/**
 * @author LB
 * @date 2017/8/3 13:48
 */
public interface AuthService {
    public User login(String username,String password);
}
