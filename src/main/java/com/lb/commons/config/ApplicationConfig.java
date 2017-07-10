package com.lb.commons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * properties文件属性值的获取
 * @author LB
 * @date 2017/6/29 16:11
 */
@Configuration
@ConfigurationProperties()
@PropertySource("classpath:application.properties")
@Component
public class ApplicationConfig {

    private String cookie_field_key;
    private String des_key;

    public String getCookie_field_key() {
        return cookie_field_key;
    }

    public void setCookie_field_key(String cookie_field_key) {
        this.cookie_field_key = cookie_field_key;
    }


    public String getDes_key() {
        return des_key;
    }

    public void setDes_key(String des_key) {
        this.des_key = des_key;
    }
}
