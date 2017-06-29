package com.lb.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * json与object的相互转换
 * @author LB
 * @date 2017/6/29 16:39
 */
@Component
public class JsonHelper {
    @Autowired
    private ObjectMapper mapper;

    /**
    * Object转Json字符串
    * @param o 转化的对象
    * @return
    */
    public String objectToJson(Object o){
        String jsonData=null;
        try {
            jsonData=mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "Object不能转换为Json字符串";
        }
        return jsonData;
    }

    /**
    * Json转Object
    * @param
    * @return
    */
    public <T> T jsonToObject(String jsonData,Class<T> cla){
        T t = null;
        try {
            t=mapper.readValue(jsonData,cla);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * list转json
     * @param list
     * @return
     */
    public String listToJson(List<?> list){
        String jsonData=null;
        return null;
    }
}
