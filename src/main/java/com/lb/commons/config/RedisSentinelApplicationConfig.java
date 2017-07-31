package com.lb.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置
 * @author LB
 * @date 2017/7/12 18:37
 */
//@Configuration
//@EnableRedisHttpSession
//@EnableConfigurationProperties({RedisConfig.class})
public class RedisSentinelApplicationConfig {
    private RedisConfig redisConfig;

    public RedisSentinelApplicationConfig(RedisConfig redisConfig){
        this.redisConfig = redisConfig;
    }

    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration(){
//        return new RedisSentinelConfiguration().master(redisSetting.getMasterName())
//                .sentinel(redisSetting.getHost1(), redisSetting.getPort1())
//                .sentinel(redisSetting.getHost2(), redisSetting.getPort2());
        RedisSentinelConfiguration master = new RedisSentinelConfiguration().master(redisConfig.getMasterName());
        redisConfig.getSentinels().forEach(item->master.sentinel((String)item.get("host"),(Integer)item.get("port")));
        return master;
    }

    @Bean(value = "redisTemplate")
    public <Object> RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory());
        // 开启事务支持
        redisTemplate.setEnableTransactionSupport(true);
        // 使用String格式序列化缓存键
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory(redisSentinelConfiguration());
    }

    /*@Bean
    public MyHttpSessionLisener myHttpSessionLisener(){
        return new MyHttpSessionLisener();
    }
    @Bean
    public SessionEventHttpSessionListenerAdapter sessionEventHttpSessionListenerAdapter(){
        List<HttpSessionListener> listeners = new ArrayList<>();
        listeners.add(myHttpSessionLisener());
        return new SessionEventHttpSessionListenerAdapter(listeners);
    }*/
}
