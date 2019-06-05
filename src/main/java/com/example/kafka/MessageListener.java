package com.example.kafka;

import com.octotelematics.core.protocol.transport.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author AFIORE
 * Created on 15/05/2019
 */

@Component
@Slf4j
public class MessageListener {

    @KafkaListener(topics = "${spring.kafka.topicIn}")
    public void onMessage(Message message)
    {
        try {
            log.debug("Message received from topicIn: " + message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
