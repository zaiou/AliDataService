package com.lb.core.controller;

import com.lb.commons.controller.BaseController;
import com.lb.commons.utils.RD;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LB on 2017/6/29.
 */
@RestController
@RequestMapping(value = "/")
public class IndexController extends BaseController {
    @RequestMapping(value = "/")
    public Map<String,Object> index(){
        return RD.success("欢迎你").renderJson();
    }
}
