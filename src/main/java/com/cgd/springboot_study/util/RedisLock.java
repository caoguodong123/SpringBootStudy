package com.cgd.springboot_study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLock {

    private Logger logger =  LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key 锁唯一标志
     * @param timeout 超时时间
     * @return
     */
    public boolean lock(String key, long timeout){

        String value = String.valueOf(timeout + System.currentTimeMillis());
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        //判断锁超时,防止死锁
        String currentValue = (String)redisTemplate.opsForValue().get(key);
        //如果锁过期
        if(!StringUtil.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //获取上一个锁的时间value
            String oldValue = (String) redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtil.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                return true;
            }
        }
        return false;
    }
    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key,String value){
        try {
            String currentValue =  (String) redisTemplate.opsForValue().get(key);
           // if(!StringUtil.isEmpty(currentValue) && currentValue.equals(value) ){
                //删除key
                redisTemplate.opsForValue().getOperations().delete(key);
           // }
           }catch (Exception e) {
                logger.error("[Redis分布式锁] 解锁出现异常了，{}",e);
        }
    }

}
