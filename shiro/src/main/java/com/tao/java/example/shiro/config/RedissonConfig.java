package com.tao.java.example.shiro.config;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * @ClassName ShiroConfig
 * @Descriiption redisson配置类
 * @Author yanjiantao
 * @Date 2019/3/20 22:47
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redisson")
public class RedissonConfig {

    private String address;
    private String password;
    private int database;

    private int connectionMinimumIdleSize = 10;
    private int idleConnectionTimeout=10000;
    private int pingTimeout=1000;
    private int connectTimeout=10000;
    private int timeout=3000;
    private int retryAttempts=3;
    private int retryInterval=1500;
    private int reconnectionTimeout=3000;
    private int failedAttempts=3;
    private int subscriptionsPerConnection=5;
    private String clientName=null;
    private int subscriptionConnectionMinimumIdleSize = 1;
    private int subscriptionConnectionPoolSize = 50;
    private int connectionPoolSize = 64;

    private boolean dnsMonitoring = false;
    private int dnsMonitoringInterval = 5000;
    /**
     *
     */
    private int thread = 8;

    private String codec="org.redisson.codec.JsonJacksonCodec";

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress(address)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setConnectionPoolSize(connectionPoolSize)
                .setDatabase(database)
                .setDnsMonitoringInterval(dnsMonitoringInterval)
                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                .setSubscriptionsPerConnection(subscriptionsPerConnection)
                .setClientName(clientName)
                .setFailedAttempts(failedAttempts)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval)
                .setReconnectionTimeout(reconnectionTimeout)
                .setTimeout(timeout)
                .setConnectTimeout(connectTimeout)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setPingTimeout(pingTimeout)
                .setPassword(password);
//        Codec codec=(Codec) ClassUtils.forName(getCodec(),ClassUtils.getDefaultClassLoader()).newInstance();
        config.setCodec(new JsonJacksonCodec());
        config.setThreads(thread);
        config.setEventLoopGroup(new NioEventLoopGroup());
//        config.setUseLinuxNativeEpoll(false);
        return Redisson.create(config);
    }
}
