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

    @Value("${cambridge.kafkaEvents.eventWordTopicName:wordEventTopic}")
    public String wordEventTopicName;

    @Value("${cambridge.kafkaEvents.eventDictionaryTopicName:dictionaryEventTopic}")
    public String dictionaryEventTopicName;

    @Value("${cambridge.kafkaEvents.eventPartitionKey:eventPartitionKey}")
    public String eventPartitionKey;

    @Value("${cambridge.kafkaEvents.eventWordConsumerGroupId:wordConsumerEventsGroup}")
    public String wordEventConsumerGroupId;

    @Value("${cambridge.kafkaEvents.eventDictionaryConsumerGroupId:dictionaryConsumerEventsGroup}")
    public String dictionaryEventConsumerGroupId;


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

    public String getWordEventTopicName() {
        return wordEventTopicName;
    }

    public void setWordEventTopicName(String wordEventTopicName) {
        this.wordEventTopicName = wordEventTopicName;
    }

    public String getWordEventConsumerGroupId() {
        return wordEventConsumerGroupId;
    }

    public void setWordEventConsumerGroupId(String wordEventConsumerGroupId) {
        this.wordEventConsumerGroupId = wordEventConsumerGroupId;
    }

    public String getEventPartitionKey() {
        return eventPartitionKey;
    }

    public void setEventPartitionKey(String eventPartitionKey) {
        this.eventPartitionKey = eventPartitionKey;
    }

    public String getDictionaryEventTopicName() {
        return dictionaryEventTopicName;
    }

    public void setDictionaryEventTopicName(String dictionaryEventTopicName) {
        this.dictionaryEventTopicName = dictionaryEventTopicName;
    }

    public String getDictionaryEventConsumerGroupId() {
        return dictionaryEventConsumerGroupId;
    }

    public void setDictionaryEventConsumerGroupId(String dictionaryEventConsumerGroupId) {
        this.dictionaryEventConsumerGroupId = dictionaryEventConsumerGroupId;
    }
}
