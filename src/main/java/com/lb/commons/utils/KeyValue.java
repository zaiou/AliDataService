package com.lb.commons.utils;

import com.alibaba.fastjson.JSON;
import com.lb.commons.constant.Constants;
import com.lb.commons.constant.Status;
import com.lb.commons.exception.EntityValidException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 键值对处理类
 * @author LB
 * @date 2017/7/31 18:06
 */
public class KeyValue extends HashMap<String, Object> {
    public static final String STATUS = "status";
    public static final String MSG = "msg";
    public static final String DATA = "data";

    /**
     * 正确返回
     *
     * @param msg
     *         返回消息
     * @return {@link KeyValue}
     */
    public static KeyValue ok(String msg) {
        return rd(Status.HTTP.OK, msg);
    }

    /**
     * 正确返回
     *
     * @param data
     *         数据
     * @return {@link KeyValue}
     */
    public static KeyValue ok(Object data) {
        return rd(Status.HTTP.OK, "", data);
    }

    /**
     * 正确返回
     *
     * @param msg
     *         返回消息
     * @param data
     *         数据
     * @return {@link KeyValue}
     */
    public static KeyValue ok(String msg, Object data) {
        return rd(Status.HTTP.OK, msg, data);
    }

    /**
     * 拒绝访问
     *
     * @param msg
     *         提示消息
     * @return {@link KeyValue}
     */
    public static KeyValue bad_request(String msg) {
        return rd(Status.HTTP.BAD_REQUEST, msg);
    }

    /**
     * 拒绝访问
     *
     * @param msg
     *         提示消息
     * @param data
     *         错误数据
     * @return {@link KeyValue}
     */
    public static KeyValue bad_request(String msg, Object data) {
        return rd(Status.HTTP.BAD_REQUEST, msg, data);
    }

    /**
     * 拒绝访问
     *
     * @param msg
     *         提示消息
     * @return {@link KeyValue}
     */
    public static KeyValue forbidden(String msg) {
        return rd(Status.HTTP.FORBIDDEN, msg);
    }

    /**
     * 拒绝访问
     *
     * @param msg
     *         提示消息
     * @param data
     *         错误数据
     * @return {@link KeyValue}
     */
    public static KeyValue forbidden(String msg, Object data) {
        return rd(Status.HTTP.FORBIDDEN, msg, data);
    }

    /**
     * 数据返回
     *
     * @param msg
     *         返回消息
     * @return {@link KeyValue}
     */
    public static KeyValue rd(int status, String msg) {
        return new KeyValue(STATUS, status).add(MSG, msg);
    }

    /**
     * 数据返回
     *
     * @param msg
     *         返回消息
     * @param data
     *         数据
     * @return {@link KeyValue}
     */
    public static KeyValue rd(int status, String msg, Object data) {
        return new KeyValue(STATUS, status).add(MSG, msg).add(DATA, data);
    }

    public static void valid(BindingResult... brs) {
        KeyValue r = br(brs);
        if (r != null) {
            throw new EntityValidException(r);
        }
    }

    public static KeyValue br(BindingResult... brs) {
        for (BindingResult br : brs) {
            if (br.hasErrors()) {
                ObjectError err = br.getAllErrors().get(0);
                String error = getErrType(err);
                if (err instanceof FieldError) {
                    FieldError f_err = (FieldError) err;
                    return bad_request(String.format("字段 %s 的值 %s %s",
                            f_err.getField(),
                            f_err.getRejectedValue(),
                            error));
                }
                return bad_request(
                        String.format("参数: %s 错误 原因: %s", err.getObjectName(), error),
                        err.getDefaultMessage());
            }
        }
        return null;
    }

    private static String getErrType(ObjectError err) {
        switch (err.getCode()) {
            case "typeMismatch":
                return "类型不匹配!";
            default:
                return err.getDefaultMessage();
        }
    }

    public KeyValue() {
    }

    public KeyValue(String key, Object value) {
        put(key, value);
    }

    public KeyValue add(String key, Object value) {
        put(key, value);
        return this;
    }

    public KeyValue del(String key) {
        remove(key);
        return this;
    }

    public <T> T getAttr(String key) {
        return (T) get(key);
    }

    public KeyValue page(Object list, com.github.pagehelper.Page page) {
        KeyValue kv = new KeyValue();
        kv.put("list", list);
        kv.setPage(page);
        put(DATA, kv);
        return this;
    }

    private KeyValue setPage(com.github.pagehelper.Page page) {
        put("num", page.getPageNum());
        put("size", page.getPageSize());
        put("count", page.getTotal());
        return this;
    }

    public String getMsg() {
        return getAttr(MSG);
    }

    public MultiValueMap toMultiValueMap() {
        LinkedMultiValueMap<String, Object> lmvm = new LinkedMultiValueMap<>();
        forEach((s, o) -> {
            LinkedList<Object> list = new LinkedList<>();
            list.add(o);
            lmvm.put(s, list);
        });
        return lmvm;
    }

    public void write(HttpServletResponse response) throws IOException {
        // 设置编码 防止乱码
        response.setHeader("Content-Type", Constants.CONTENT_TYPE);
        response.getOutputStream().write(toJson().getBytes("UTF-8"));
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        return toJson();
    }
}
