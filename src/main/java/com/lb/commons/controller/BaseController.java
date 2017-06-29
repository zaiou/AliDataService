package com.lb.commons.controller;

import com.lb.commons.config.ApplicationConfig;
import com.lb.commons.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LB on 2017/6/29.
 */

public class BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApplicationConfig config;
    @Autowired
    private JsonHelper jsonHelper;

    /**
     * 获取参数，不在存默认空字符串
     */
    public String getPara(String name){
        String result = request.getParameter(name);
        return result != null && !"".equals(result) ? result : "";
    }

    /** 获取页码 */
    public int getPageNo(){
        String pageNo =request.getParameter("pageNo");
        return pageNo!=null?Integer.valueOf(pageNo):1;
    }

    /** 获取页条数 */
    public int getPageSize(){
        String pageSize=request.getParameter("pageSize");
        return pageSize!=null?Integer.valueOf(pageSize):20;
    }

    /**
     * 获取发送者id
     * 2017年5月31日 上午10:46:02
     */
    public String getSellerMemberId(){
        return "xxx";
    }

    /**
    * 管理员id
    * @param
    * @return
    */
    public int getAdminId(){
        return 0;
    }

    /**
    * 根据key,获取相应cookie值
    * @param
    * @return
    */
    public String getCookie(String key){
        Cookie[] cookies= request.getCookies();
        Cookie cookie=null;
        if(cookies!=null&&cookies.length>0) {
            for (Cookie coo : cookies)
                if (cookie.getName().equals(key))
                    cookie = coo;
        }
        return cookie!=null?cookie.getValue():null;
    }

    /**
    * cookie中获取用户信息
    * @param
    * @return
    */
    public Map<String,String> getUserInfo(){
        Map<String,String> userInfo=new HashMap<>();
        String userinfo=this.getCookie(config.getCookie_field_key());
        userInfo= jsonHelper.jsonToObject(userinfo,Map.class);
        return userInfo;
    }
}

