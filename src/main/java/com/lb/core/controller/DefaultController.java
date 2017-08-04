package com.lb.core.controller;

import com.lb.commons.constant.Status;
import com.lb.commons.utils.KeyValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认控制器 用来拦截返回不存在的方法
 * @author LB
 * @date 2017/8/4 12:28
 */
@RequestMapping
@RestController
public class DefaultController {
    @RequestMapping("/**")
    public KeyValue notFound(HttpServletRequest request) {
        return KeyValue.rd(Status.HTTP.NOT_FOUND, "路径未找到!")
                .add("path", request.getServletPath())
                .add("method", request.getMethod())
                .add("time", System.currentTimeMillis());
    }
}
