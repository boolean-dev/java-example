package com.tao.java.example.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName KafkaConsumer
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/6 17:13
 **/
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = {"my-topics"})
    public void consumer(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }
    }
}
