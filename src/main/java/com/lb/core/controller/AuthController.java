package com.lb.core.controller;

import com.lb.commons.constant.Constants;
import com.lb.commons.constant.Status;
import com.lb.commons.kits.UserKit;
import com.lb.commons.utils.KeyValue;
import com.lb.core.info.entity.User;
import com.lb.core.service.impl.AuthServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 登录控制器
 * @author LB
 * @date 2017/8/2 10:11
 */
@RestController
@RequestMapping(value = "auth")
public class AuthController {
    private Logger logger=Logger.getLogger(AuthController.class);

    private AuthServiceImpl authServiceImpl;

    private AuthController(AuthServiceImpl authServiceImpl){
        this.authServiceImpl=authServiceImpl;
    }

    @RequestMapping(value = "login")
    public KeyValue login(String username,String password){
        boolean flag=false;
        logger.info("登录");
        if(StringUtils.isAnyEmpty(username,password)){
            return KeyValue.rd(Status.HTTP.FORBIDDEN, Constants.Auth.LOGIN_FAIL);
        }
        User user=authServiceImpl.login(username,password);
        if(user!=null){
            flag=true;
            UserKit
        }
        return KeyValue.rd(1,"",user);
    }
}
