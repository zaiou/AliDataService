package com.lb.commons.controller;

import com.lb.commons.config.ApplicationConfig;
import com.lb.commons.utils.DESHelper;
import com.lb.commons.utils.Fn;
import com.lb.commons.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LB on 2017/6/29.
 */

public class BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
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
     * 保存用户登录信息到cookie
     * @return
     */
    public void setCookie(String key,String value) {
        Cookie cookie=new Cookie(key,value);
        //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
        cookie.setPath("/");
        cookie.setMaxAge(604800);
        response.addCookie(cookie);
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

    /**
    * 根据key,获取相应cookie值
    * @param
    * @return
    */
    public String getCookie(String key){
        Cookie[] cookies= request.getCookies();
        String cookieVal=null;
        if(!Fn.isStrEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie!=null&&cookie.getName().equals(key)){
                    cookieVal =this.decryptData(cookie.getValue());
                }
            }
        }
        return cookieVal;
    }

    /**
     * 删除cookie
     */
    public void removeCookie(String key){
        Cookie cookie=new Cookie(key,null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 加密
     * @param encryptdata
     * @return
     */
    public String encryptData(String encryptdata) {
        String encrypt=null;
        try {
            encrypt = DESHelper.encrypt(encryptdata, config.getDes_key());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypt;
    }

    /**
     * 解密
     * @param decryptdata
     * @return
     */
    public String decryptData(String decryptdata){
        String encrypt=null;
        try {
            encrypt = DESHelper.decrypt(decryptdata, config.getDes_key());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypt;
    }
}

