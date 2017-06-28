package com.lb.commons.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LB on 2017/6/29.
 */

public class BaseController {
    @Autowired
    private HttpServletRequest request;

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
}
