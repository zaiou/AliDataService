package com.lb.core.controller;

import com.lb.commons.controller.BaseController;
import com.lb.commons.utils.RD;
import com.lb.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LB on 2017/6/29.
 */
@RestController
@RequestMapping(value = "/")
public class IndexController extends BaseController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/")
    public Map<String,Object> index(){
        System.out.println(orderService.getOrderList());
        System.out.println(this.getUserInfo());
        return RD.success("欢迎你").renderJson();
    }
}
