package com.lb.commons.interceptor;

import com.lb.commons.annotation.PowerBind;
import com.lb.commons.controller.BaseController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限拦截器
 * @author LB
 * @date 2017/6/29 11:23
 */
public class Permission extends  HandlerInterceptorAdapter{

    /**
    * 预处理
    * @return
    */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("权限拦截器");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        PowerBind power=method.getAnnotation(PowerBind.class);
        if(power==null){
            return true;
        }else{
            String [] powerName=power.value();
            BaseController ctrl= (BaseController) handlerMethod.getBean();
            if(!hasPermission(ctrl.getAdminId(),powerName)){
                return false;
            }
        }
        return true;
    }

    public Boolean hasPermission(int adminId,String[] powerName){
        return true;
    }
}
