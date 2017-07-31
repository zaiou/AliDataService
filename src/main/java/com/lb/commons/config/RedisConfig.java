package com.lb.commons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * redis属性获取
 * @author LB
 * @date 2017/7/12 18:27
 */
@ConfigurationProperties("redis")
public class RedisConfig {
    private List<Map<String,Object>> sentinels;

    private String masterName;

    public List<Map<String, Object>> getSentinels() {
        return sentinels;
    }

    public void setSentinels(List<Map<String, Object>> sentinels) {
        this.sentinels = sentinels;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }
}
