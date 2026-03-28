package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> clazz) {
        try{
            Object value = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(value, clazz);
        } catch(Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }
    public void set(String key,Object value,Long ttl) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, json, ttl , TimeUnit.MINUTES);
        } catch(Exception e){
            log.error(e.getMessage(), e);
        }
    }
}
