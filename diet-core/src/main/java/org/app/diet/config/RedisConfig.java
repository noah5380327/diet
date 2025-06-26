package org.app.diet.config;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.app.diet.property.RedisProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperty redisProperty;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisProperty.getHost());
        config.setPort(redisProperty.getPort());
        if (StrUtil.isNotBlank(redisProperty.getUsername())) {
            config.setUsername(redisProperty.getUsername());
        }
        if (StrUtil.isNotBlank(redisProperty.getPassword())) {
            config.setPassword(redisProperty.getPassword());
        }

        return new LettuceConnectionFactory(config);
    }

    @Bean
    public KeyGenerator core() {
        return (o, method, objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(method.getName());
            if (objects.length > 0) {
                stringBuilder.append("::");
            }
            for (int i = 0; i < objects.length; i++) {
                Object obj = objects[i];
                stringBuilder.append(obj.toString());
                if (i != objects.length - 1) {
                    stringBuilder.append(",");
                }
            }

            return stringBuilder.toString();
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        GenericJackson2JsonRedisSerializer serializer = this.getValueSerializer();
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        RedisSerializationContext.SerializationPair<?> serializationPair = RedisSerializationContext
                .SerializationPair.fromSerializer(serializer);
        redisCacheConfiguration = redisCacheConfiguration
                .serializeValuesWith(serializationPair)
                .entryTtl(Duration.ofSeconds(redisProperty.getExpiredTime()));

        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        GenericJackson2JsonRedisSerializer serializer = this.getValueSerializer();
        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }

    private GenericJackson2JsonRedisSerializer getValueSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType(Object.class)
                .build();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }
}
