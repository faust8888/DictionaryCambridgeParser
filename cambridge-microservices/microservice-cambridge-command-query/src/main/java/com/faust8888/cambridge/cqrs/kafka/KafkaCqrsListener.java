package com.faust8888.cambridge.cqrs.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaCqrsListener {

    @KafkaListener(topics = "eventTopic", groupId = "consumerEventsGroup")
    public void listen(String message) {
        System.out.println("Received Messasge in group foo: " + message);
    }

}
