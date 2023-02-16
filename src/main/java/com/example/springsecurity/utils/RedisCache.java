package com.example.springsecurity.utils;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wxz
 * @date 16:29 2023/2/16
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Component
public class RedisCache {

    @Resource
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本对象
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @author wxz
     * @date 16:30 2023/2/16
     */
    public <T> void settCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本对象
     *
     * @param key     缓存的键值
     * @param value   缓存的值
     * @param timeout 过期时间
     * @param unit    时间单位
     * @author wxz
     * @date 16:32 2023/2/16
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 设置有效时间
     *
     * @param key     缓存的键值
     * @param timeout 过期时间
     * @return boolean
     * @author wxz
     * @date 16:37 2023/2/16
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     缓存的键值
     * @param timeout 过期时间
     * @param unit    单位
     * @return boolean
     * @author wxz
     * @date 16:37 2023/2/16
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 获取缓存基本对象
     *
     * @param key 缓存的键值
     * @return T
     * @author wxz
     * @date 16:39 2023/2/16
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key 缓存的键值
     * @return boolean
     * @author wxz
     * @date 16:40 2023/2/16
     */
    public boolean delete(final String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return long
     * @author wxz
     * @date 16:41 2023/2/16
     */
    public long delete(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List对象
     *
     * @param key  缓存的键值
     * @param list 待缓存的List数据
     * @return long
     * @author wxz
     * @date 16:44 2023/2/16
     */
    public <T> long setCacheList(final String key, final List<T> list) {
        Long count = redisTemplate.opsForList().rightPushAll(key, list);
        return count == null ? 0 : count;
    }

    /**
     * 获取缓存的list对象
     *
     * @param key 缓存的键值
     * @return java.util.List<T>
     * @author wxz
     * @date 16:45 2023/2/16
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key 缓存的键值
     * @param set 缓存的数据
     * @return org.springframework.data.redis.core.BoundSetOperations<java.lang.String, T>
     * @author wxz
     * @date 16:48 2023/2/16
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> set) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            setOperation.add(iterator.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的Set
     *
     * @param key 键值
     * @return java.util.Set<T>
     * @author wxz
     * @date 16:49 2023/2/16
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存map
     *
     * @param key 键值
     * @param map 数据
     * @author wxz
     * @date 16:50 2023/2/16
     */
    public <T> void setCacheMap(final String key, final Map<String, T> map) {
        if (map != null) {
            redisTemplate.opsForHash().putAll(key, map);
        }
    }

    /**
     * 获取缓存的Map
     *
     * @param key 键值
     * @return java.util.Map<java.lang.String, T>
     * @author wxz
     * @date 16:52 2023/2/16
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   键值
     * @param hkey  Hash值
     * @param value 值
     * @author wxz
     * @date 16:55 2023/2/16
     */
    public <T> void setCacheMapValue(final String key, final String hkey, final T value) {
        redisTemplate.opsForHash().put(key, hkey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  键值
     * @param hkey Hash键值
     * @return T
     * @author wxz
     * @date 16:57 2023/2/16
     */
    public <T> T getCacheMapValue(final String key, final String hkey) {
        HashOperations<String, String, T> ops = redisTemplate.opsForHash();
        return ops.get(key, hkey);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key  键值
     * @param hkey Hash键值
     * @author wxz
     * @date 16:57 2023/2/16
     */
    public void delCacheMapValue(final String key, final String hkey) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hkey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   键值
     * @param hkeys Hash键集合
     * @return java.util.List<T>
     * @author wxz
     * @date 17:00 2023/2/16
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hkeys) {
        return redisTemplate.opsForHash().multiGet(key, hkeys);
    }

    /**
     * 获取缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return java.util.Collection<java.lang.String>
     * @author wxz
     * @date 17:01 2023/2/16
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
