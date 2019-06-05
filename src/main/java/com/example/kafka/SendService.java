package com.example.kafka;

import com.google.common.util.concurrent.AtomicDouble;
import com.octotelematics.core.protocol.Event;
import com.octotelematics.core.protocol.events.motor.LongEventStatus;
import com.octotelematics.core.protocol.events.motor.Trip;
import com.octotelematics.core.protocol.measures.motor.Position;
import com.octotelematics.core.protocol.transport.Message;
import com.octotelematics.otp.serialization.SerializationException;
import com.octotelematics.serialization.transport.EventSerialization;
import com.octotelematics.serialization.transport.MessageSerialization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author AFIORE
 * Created on 05/06/2019
 */
@Service
@Slf4j
public class SendService {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Value("${spring.kafka.topicOut}")
    private String topic;


    public void send() {
        try {
            EventSerialization ser = EventSerialization.instance();
            ser.configure("ciao", "mamma");

            Message m = EventSerialization.instance().build(generateEvent(), MessageSerialization.Serialization.avro, MessageSerialization.Encoding.gzip, "tenant1", "123").getMessage();

            org.springframework.messaging.Message<Message> message = MessageBuilder
                    .withPayload(m)
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .build();

            kafkaTemplate.send(message);

            log.debug("Message sent. " + m);
        } catch (SerializationException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private Event generateEvent() {
        AtomicDouble timestamp = new AtomicDouble(System.currentTimeMillis());
        Random r = new Random();

        Trip trip = new Trip().withDevice("123")
                .withId(1l)
                .withStatus(LongEventStatus.START)
                .atTimestamp(timestamp.addAndGet(1000));

        for (int j = 0; j < 100; j++) {
            trip.addPosition(new Position().withId(System.nanoTime())
                    .withLatitude(r.nextDouble() * 100)
                    .withLongitude(r.nextDouble() * 100)
                    .atTimestamp(timestamp.addAndGet(1000))
                    .withSpeed(r.nextInt(150))
                    .withHeading(r.nextDouble() * 360)
                    .withQuality(r.nextInt(3))
                    .withSatellites(r.nextInt(15)));
        }

        return trip;

//        EventSerialization ser = EventSerialization.instance();
//        ser.configure("ciao", "mamma");
//
//        Entry build = ser.build(trip, Serialization.avro, Encoding.gzip,"tenant");
//
//        return Serializers.getInstance("avro")
//                .serialize(build.getMessage());
    }
}
