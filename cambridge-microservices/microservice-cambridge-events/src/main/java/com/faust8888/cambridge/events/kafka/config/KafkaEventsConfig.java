package com.faust8888.cambridge.events.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;


@Configuration
@RefreshScope
public class KafkaEventsConfig {

    @Value("${cambridge.kafkaEvents.eventsKafkaBroker:localhost:9092}")
    public String eventsKafkaBroker;

    @Value("${cambridge.kafkaEvents.clientId:cambridgeevents}")
    public String clientId;

    @Value("${cambridge.kafkaEvents.eventTopicName:eventTopic}")
    public String eventTopicName;

    @Value("${cambridge.kafkaEvents.eventPartitionKey:eventPartitionKey}")
    public String eventPartitionKey;

    @Value("${cambridge.kafkaEvents.eventConsumerGroupId:consumerEventsGroup}")
    public String eventConsumerGroupId;


    public String getEventsKafkaBroker() {
        return eventsKafkaBroker;
    }

    public void setEventsKafkaBroker(String eventsKafkaBroker) {
        this.eventsKafkaBroker = eventsKafkaBroker;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEventTopicName() {
        return eventTopicName;
    }

    public void setEventTopicName(String eventTopicName) {
        this.eventTopicName = eventTopicName;
    }

    public String getEventConsumerGroupId() {
        return eventConsumerGroupId;
    }

    public void setEventConsumerGroupId(String eventConsumerGroupId) {
        this.eventConsumerGroupId = eventConsumerGroupId;
    }

    public String getEventPartitionKey() {
        return eventPartitionKey;
    }

    public void setEventPartitionKey(String eventPartitionKey) {
        this.eventPartitionKey = eventPartitionKey;
    }
}
