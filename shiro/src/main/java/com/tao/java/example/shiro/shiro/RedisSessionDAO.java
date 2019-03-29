package com.tao.java.example.shiro.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisSessionDAO
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/28 15:57
 **/
@Slf4j
public class RedisSessionDAO extends AbstractSessionDAO {

    private long sessionLive;
    private String sessionKeyPrefix;
//    private RedisTemplate redisTemplate;
    private RedissonClient redisson;

    /**
     * 创建session
     * @param session   session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        log.info("-------------->创建session，存入到session");
//        Serializable sessionId = generateSessionId(session);
//        assignSessionId(session, sessionId);
//        redisTemplate.opsForValue().set(sessionKeyPrefix + sessionId, session, sessionLive, TimeUnit.MINUTES);
//        return sessionId;
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        RBucket<Session> rBucket = redisson.getBucket(sessionKeyPrefix + sessionId);
        rBucket.set(session, sessionLive, TimeUnit.MINUTES);
        log.info("session={}", session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("------------->从浏览器中读取session");
//        Object session = redisTemplate.opsForValue().get(sessionKeyPrefix + sessionId);
//        return (Session) redisTemplate.opsForValue().get(sessionKeyPrefix + sessionId);
        RBucket<Session> rBucket = redisson.getBucket(sessionKeyPrefix + sessionId);
        if (rBucket.isExists()) {
            return rBucket.get();
        }
        log.info("session={}", rBucket.get());
        return null;
    }

    @Override
    public void update(Session session) {
        log.info("------------->更新session");
//        this.redisTemplate.opsForValue().set(sessionKeyPrefix + session.getId(), session, sessionLive, TimeUnit.MINUTES);
        log.info("session={}", session);
        RBucket<Session> rBucket = redisson.getBucket(sessionKeyPrefix + session.getId());
        rBucket.set(session, sessionLive, TimeUnit.MINUTES);
    }

    @Override
    public void delete(Session session) {
        log.info("------------->删除session");
        if (session == null || session.getId() == null) {
            return;
        }
//        this.redisTemplate.delete(sessionKeyPrefix + session.getId());
        RBucket<Session> rBucket = redisson.getBucket(sessionKeyPrefix + session.getId());
        rBucket.delete();
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.info("------------->得到活跃的session");
        /*Set<Serializable> keys = redisTemplate.keys(sessionKeyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            for (Serializable key : keys) {
                Session s = (Session) redisTemplate.opsForValue().get(key);
                sessions.add(s);
            }
        }*/
        Set<Session> sessions = new HashSet<>(10);
        this.redisson.getKeys().getKeysByPattern(sessionKeyPrefix + "*").forEach(key -> {
            RBucket<Session> buckets = this.redisson.getBucket(key);
            sessions.add(buckets.get());
        });
        return sessions;
    }


    public void setSessionLive(long sessionLive) {
        this.sessionLive = sessionLive;
    }

    public void setSessionKeyPrefix(String sessionKeyPrefix) {
        this.sessionKeyPrefix = sessionKeyPrefix;
    }

    /*public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }*/

    public void setRedisson(RedissonClient redisson) {
        this.redisson = redisson;
    }
}
