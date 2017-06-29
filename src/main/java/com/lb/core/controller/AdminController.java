package com.lb.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lb.commons.config.ApplicationConfig;
import com.lb.commons.controller.BaseController;
import com.lb.commons.utils.JsonHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LB
 * @date 2017/6/29 15:53
 */
@RestController
public class AdminController extends BaseController {

    private static Logger logger=Logger.getLogger(AdminController.class);
    @Autowired
    private ApplicationConfig config;
    @Autowired
    private JsonHelper jsonHelper;
    @RequestMapping(value = "/login")
    public Map<String,Object> login() throws Exception{
        String username=this.getPara("username");
        String password=this.getPara("password");
        if(false){
            logger.info("用户名或者密码错误");
        }
        logger.info("用户名或者密码错误");
        Map<String,Object> userMap=new HashMap<>();
        userMap.put("username",username);
        userMap.put("password",password);

        Cookie userCookie=new Cookie(config.getCookie_field_key(),jsonHelper.objectToJson(userMap));
        return null;
    }
}
