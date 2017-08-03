package com.lb.commons.constant;

/**
 * @author LB
 * @date 2017/7/31 18:09
 */
public class Constants {
    private Constants(){}
    public static final String CONTENT_TYPE = "application/json; charset=utf-8";
    public static final String CHARSET_UTF8 = "UTF-8";

    public static class Auth {
        private Auth(){}
        public static final String USER_NOT_LOGIN = "用户未登录22!";
        public static final String LOGIN_SUCCESS = "登录成功!";
        public static final String LOGIN_FAIL = "用户名或密码错误!";
        public static final String EMPTY_USER_OR_PWD = "用户名或密码为空!";
        public static final String USER_EXIST = "用户名已存在!";
        public static final String USER_NOT_ADMIN = "当前非管理员用户!";
    }
}
