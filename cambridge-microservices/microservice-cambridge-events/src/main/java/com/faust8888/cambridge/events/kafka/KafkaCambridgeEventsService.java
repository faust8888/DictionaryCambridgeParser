package com.faust8888.cambridge.events.kafka;

import com.faust8888.cambridge.events.kafka.config.KafkaEventsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaCambridgeEventsService {

    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaEventsConfig kafkaEventsConfig;

    @Autowired
    public KafkaCambridgeEventsService(KafkaTemplate<String, String> kafkaTemplate, KafkaEventsConfig kafkaEventsConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaEventsConfig = kafkaEventsConfig;
    }

    public void sendEventMessage(String message) {
        kafkaTemplate.send(kafkaEventsConfig.getEventTopicName(), kafkaEventsConfig.getEventPartitionKey(), message);
    }

}
