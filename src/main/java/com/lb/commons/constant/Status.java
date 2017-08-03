package com.lb.commons.constant;

/**
 * @author LB
 * @date 2017/7/31 18:08
 */
public class Status {
    public static class HTTP {
        public static final int OK = 200;
        public static final int NOT_MODIFIED = 304;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int METHOD_NOT_ALLOWED = 405;
        public static final int CONFLICT = 409;
        public static final int INTERNAL_SERVER_ERROR = 500;
    }
}
