package com.faust8888.cambridge.events.kafka;

import com.faust8888.cambridge.events.kafka.config.KafkaEventsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaCambridgeEventService {

    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaEventsConfig kafkaEventsConfig;

    @Autowired
    public KafkaCambridgeEventService(
            final KafkaTemplate<String, String> kafkaTemplate,
            final KafkaEventsConfig kafkaEventsConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaEventsConfig = kafkaEventsConfig;
    }

    public void sendWordEventMessage(final String message) {
        kafkaTemplate.send(kafkaEventsConfig.getWordEventTopicName(), kafkaEventsConfig.getEventPartitionKey(), message);
    }

    public void sendDictionaryEventMessage(final String message) {
        kafkaTemplate.send(kafkaEventsConfig.getDictionaryEventTopicName(), kafkaEventsConfig.getEventPartitionKey(), message);
    }

}
