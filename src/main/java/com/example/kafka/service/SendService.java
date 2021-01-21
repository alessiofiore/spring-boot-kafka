package com.example.kafka.service;

import com.example.kafka.messaging.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendService {

    @Autowired
    private KafkaTemplate<String, MyMessage> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;


    public void send(String value) {
        MyMessage myMessage = new MyMessage(value);

        org.springframework.messaging.Message<MyMessage> message = MessageBuilder
                .withPayload(myMessage)
                .setHeader(KafkaHeaders.TOPIC, topic)
                // .setHeader()
                // ...
                .build();
        kafkaTemplate.send(message);

        // alternative with topic
        // kafkaTemplate.send(topic, myMessage);

        // alternative with partition and key
        // kafkaTemplate.send(topic, 0 /* partition */ , "someKey", myMessage);

        log.debug("Message sent. " + myMessage);
    }
}
