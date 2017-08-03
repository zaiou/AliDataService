package com.lb.commons.handler;

import com.alibaba.fastjson.JSON;
import com.lb.commons.constant.Status;
import com.lb.commons.exception.EntityValidException;
import com.lb.commons.exception.ForbiddenException;
import com.lb.commons.kits.SessionKit;
import com.lb.commons.utils.KeyValue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LB
 * @date 2017/7/31 16:32
 */
@ControllerAdvice //把异常控制器应用到所有控制器
public class GlobeExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger= LoggerFactory.getLogger(GlobeExceptionHandler.class);

    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public KeyValue handleForbiddenException(ForbiddenException ex) {
        doLogger(ex);
        return KeyValue.forbidden(ex.getMessage());
    }


    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public KeyValue handleForbiddenException(MultipartException ex) {
        doLogger(ex);
        return KeyValue.forbidden("文件大小超出系统限制(2M)");
    }

    @ExceptionHandler(EntityValidException.class)
    @ResponseBody
    public KeyValue handleValidException(EntityValidException ex) {
        // 实体认证异常不需要打印
        return ex.getKeyValue();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public KeyValue handleControllerException(Throwable ex) {
        doLogger(ex);
        return KeyValue.rd(Status.HTTP.INTERNAL_SERVER_ERROR, ex.getClass().getName() + ": " + ex.getMessage());
    }

    private void doLogger(Throwable ex) {
        HttpServletRequest request = SessionKit.getRequest();
        String realIp = request.getHeader("X-Real-IP");
        if (logger.isErrorEnabled()) {
            logger.info(String.format("\n%-15s %-6s %s %s",
                    StringUtils.isEmpty(realIp) ? request.getRemoteAddr() : realIp,
                    request.getMethod(),
                    request.getRequestURI(),
                    JSON.toJSONString(request.getParameterMap())));
            logger.error("", ex);
        }
    }
}
