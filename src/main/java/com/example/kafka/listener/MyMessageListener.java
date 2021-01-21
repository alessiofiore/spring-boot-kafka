package com.example.kafka.listener;

import com.example.kafka.messaging.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyMessageListener {

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void onMessage(MyMessage message)
    {
        try {
            log.debug("Message received: " + message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
