package com.tao.java.example.shiro.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.redisson.api.RBucket;
import org.redisson.api.RBuckets;
import org.redisson.api.RedissonClient;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 自定义redisCache
 * Created by Administrator on 2018/2/12.
 */
@Slf4j
public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private long cacheLive;
    private String cacheKeyPrefix;
//    private RedisTemplate redisTemplate;
    private RedissonClient redisson;

    /*public ShiroRedisCache(RedisTemplate redisTemplate, long cacheLive, String cachePrefix) {
        this.redisTemplate = redisTemplate;
        this.cacheLive = cacheLive;
        this.cacheKeyPrefix = cachePrefix;
    }*/

    public ShiroRedisCache(long cacheLive, String cacheKeyPrefix, RedissonClient redisson) {
        this.cacheLive = cacheLive;
        this.cacheKeyPrefix = cacheKeyPrefix;
        this.redisson = redisson;
    }

    @Override
    public V get(K k) throws CacheException {
        log.info("---------------->ShiroRedisCache-------通过key得到redis数据库的值");
        RBucket<V> vrBucket =  this.redisson.getBucket(this.getRedisCacheKey(k));
        return vrBucket.get();
//        return (V) this.redisTemplate.opsForValue().get(this.getRedisCacheKey(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        log.info("---------------->ShiroRedisCache---------设置redis下的key和value的值，将登陆信息存入到redis");
        RBucket<V> vrBucket =  this.redisson.getBucket(this.getRedisCacheKey(k));
        vrBucket.set(v,cacheLive,TimeUnit.MINUTES);
        return vrBucket.get();
//        redisTemplate.opsForValue().set(this.getRedisCacheKey(k), v, cacheLive, TimeUnit.MINUTES);
//        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        log.info("---------------->ShiroRedisCache---------通过key移除value");
//        V obj = (V) this.redisTemplate.opsForValue().get(this.getRedisCacheKey(k));
//        redisTemplate.delete(this.getRedisCacheKey(k));
//        return obj;
        RBucket<V> vrBucket =  this.redisson.getBucket(this.getRedisCacheKey(k));
        V value = vrBucket.get();
        vrBucket.delete();
        return value;
    }

    @Override
    public void clear() throws CacheException {
        log.info("---------------->ShiroRedisCache---------清空redis下的登录信息");
        this.redisson.getKeys().getKeysByPattern(this.cacheKeyPrefix + "*").forEach(value -> {
            this.redisson.getBuckets().delete(value);
        });
//        Set keys = this.redisTemplate.keys(this.cacheKeyPrefix + "*");
//        if (null != keys && keys.size() > 0) {
//            Iterator itera = keys.iterator();
//            this.redisTemplate.delete(itera.next());
//        }
    }

    @Override
    public int size() {
        log.info("---------------->ShiroRedisCache---------得到当前登录人数");
//        Set<K> keys = this.redisTemplate.keys(this.cacheKeyPrefix + "*");
//        return keys.size();
        return (int)this.redisson.getKeys().getKeysByPattern(this.cacheKeyPrefix + "*").spliterator().estimateSize();

    }

    @Override
    public Set<K> keys() {
        log.info("---------------->ShiroRedisCache---------得到当前所有key");
//        return this.redisTemplate.keys(this.cacheKeyPrefix + "*");
        Set<K> keys = new HashSet<>(10);
        this.redisson.getKeys().getKeysByPattern(this.cacheKeyPrefix + "*").forEach(key -> keys.add((K)key));
        return keys;
    }

    @Override
    public Collection<V> values() {
        log.info("---------------->ShiroRedisCache---------得到当前所有的values");
       /* Set<K> keys = this.redisTemplate.keys(this.cacheKeyPrefix + "*");
        Set<V> values = new HashSet<V>(keys.size());
        for (K key : keys) {
            values.add((V) this.redisTemplate.opsForValue().get(this.getRedisCacheKey(key)));
        }
        return values;*/
        Set<V> values = new HashSet<V>(10);
        this.redisson.getKeys().getKeysByPattern(this.cacheKeyPrefix + "*").forEach(key -> {
            RBucket<V> buckets = this.redisson.getBucket(key);
            values.add(buckets.get());
        });
        return values;

    }

    private String getRedisCacheKey(K key) {
        Object redisKey = this.getStringRedisKey(key);
        if (redisKey instanceof String) {
            return this.cacheKeyPrefix + redisKey;
        } else {
            return String.valueOf(redisKey);
        }
    }

    private Object getStringRedisKey(K key) {
        Object redisKey;
        if (key instanceof PrincipalCollection) {
            redisKey = this.getRedisKeyFromPrincipalCollection((PrincipalCollection) key);
        } else {
            redisKey = key.toString();
        }
        return redisKey;
    }

    private Object getRedisKeyFromPrincipalCollection(PrincipalCollection key) {
        List<String> realmNames = this.getRealmNames(key);
        Collections.sort(realmNames);
        Object redisKey = this.joinRealmNames(realmNames);
        return redisKey;
    }

    private List<String> getRealmNames(PrincipalCollection key) {
        ArrayList<String> realmArr = new ArrayList<>();
        Set<String> realmNames = key.getRealmNames();
        Iterator<String> i$ = realmNames.iterator();
        while (i$.hasNext()) {
            String realmName = i$.next();
            realmArr.add(realmName);
        }
        return realmArr;
    }

    private Object joinRealmNames(List<String> realmArr) {
        StringBuilder redisKeyBuilder = new StringBuilder();
        for (int i = 0; i < realmArr.size(); ++i) {
            String s = realmArr.get(i);
            redisKeyBuilder.append(s);
        }
        String redisKey = redisKeyBuilder.toString();
        return redisKey;
    }



}
