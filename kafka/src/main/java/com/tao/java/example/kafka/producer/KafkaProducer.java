package com.tao.java.example.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @ClassName KafkaProducer
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/6 17:07
 **/

@Slf4j
@Component
@EnableScheduling
public class KafkaProducer {

    /*@Autowired
    private KafkaTemplate kafkaTemplate;*/

    @Scheduled(cron = "00/1 * * * * ?")
    public void send(){
        String message = LocalDateTime.now().toString();
        log.info("message={}", message);
//        kafkaTemplate.send("my-topics",message);

//        String message = UUID.randomUUID().toString() + LocalDateTime.now().toString();
//        log.info("--------message={}", message);
//        ListenableFuture future = kafkaTemplate.send("my-topics", message);
//        future.addCallback(o -> log.info("send-消息发送成功{}",message), throwable -> log.error("消息发送失败:{}", message));
    }


}
