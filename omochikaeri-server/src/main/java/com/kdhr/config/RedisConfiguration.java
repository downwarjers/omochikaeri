package com.kdhr.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("產生RedisTemplate {}}", redisConnectionFactory.getConnection());
        RedisTemplate redisTemplate = new RedisTemplate();
        //設定連線的Connection Factory
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //設定字串KEY、Value序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * 預設已經做好序列化的RedisTemplate
     *
     * @param redisConnectionFactory
     * @return
     */
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        log.info("StringRedisTemplate {}}", redisConnectionFactory.getConnection());
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
//        return stringRedisTemplate;
//    }
}
