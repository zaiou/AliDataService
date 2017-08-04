package com.lb.commons.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * @author 喵♂呜
 * @since 2017/6/2
 */
public class DebugInterceptor extends HandlerInterceptorAdapter {
    private Logger log = Logger.getLogger(DebugInterceptor.class.getCanonicalName());


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String realIp = request.getHeader("X-Real-IP");
        log.info(String.format("\n%-15s %-6s %s %s %s",
                               StringUtils.isEmpty(realIp) ? request.getRemoteAddr() : realIp,
                               request.getMethod(),
                               response.getStatus(),
                               request.getRequestURI(),
                               JSON.toJSONString(request.getParameterMap())));
    }
}
