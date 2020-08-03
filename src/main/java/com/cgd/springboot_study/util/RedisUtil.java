package com.cgd.springboot_study.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    private Logger logger =  LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Description: 批量删除缓存
     * @Author: hj
     * @Date: 17:13 2017/10/24
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * @Description: 批量删除缓存(通配符)
     * @Author: hj
     * @Date: 16:52 2017/10/24
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * @Description: 删除缓存
     * @Author: hj
     * @Date: 16:51 2017/10/24
     */
    public void remove(final String key) {
        try {
            if (exists(key)) {
                redisTemplate.delete(key);
                logger.info("删除缓存");
            }
        }catch (Exception e){
        }
    }

    /**
     * @Description: 判断缓存中是否有对应的value
     * @Author: hj
     * @Date: 16:50 2017/10/24
     */
    public boolean exists(final String key) {
            return redisTemplate.hasKey(key);
    }

    /**
     * @Description: 读取缓存
     * @Author: hj
     * @Date: 16:49 2017/10/24
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @Description: 写入缓存
     * @Author: hj
     * @Date: 16:48 2017/10/24
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
            logger.info("result===="+result);
        } catch (Exception e) {
            logger.error("result===="+result);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description: 写入缓存(可以配置过期时间)
     * @Author: hj
     * @Date: 16:46 2017/10/24
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
            logger.info("result===="+result);
        } catch (Exception e) {
            logger.error("result===="+result);
        }
        return result;
    }
    /**
     * 实现命令：expire 设置过期时间，单位秒
     * @param key
     * @return
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }


     /*
     * @Author: zhanglin
     * @Date:  2019/9/6
     * @Description：如果过期时间小于半小时  则需要重新计算
     */
    public Boolean getExpire(String key){
        Long expire = redisTemplate.getExpire(key);
        long i = expire / (1000 * 60 * 30);
        if(i>=1){
            return true;  //不需要重新计算
        }
        return false; //需要重新计算
    }
}
