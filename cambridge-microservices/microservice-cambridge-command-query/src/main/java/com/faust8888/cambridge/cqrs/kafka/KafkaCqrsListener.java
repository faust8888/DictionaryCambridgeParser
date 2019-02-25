package com.faust8888.cambridge.cqrs.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faust8888.cambridge.cqrs.command.service.CommandHandlerService;
import com.faust8888.cambridge.cqrs.command.service.CqrsMapperService;
import com.faust8888.cambridge.events.DictionaryEvent;
import com.faust8888.cambridge.events.WordEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class KafkaCqrsListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHandlerService.class);

    private CommandHandlerService commandHandler;
    private CqrsMapperService cqrsMapperService;

    @Autowired
    public KafkaCqrsListener(final CommandHandlerService commandHandler, final CqrsMapperService cqrsMapperService) {
        this.commandHandler = commandHandler;
        this.cqrsMapperService = cqrsMapperService;
    }

    @KafkaListener(topics = "wordEventTopic", groupId = "consumerEventsGroup")
    public void listenWordEvent(final String message) throws IOException {
        try {
            final WordEvent wordEvent = cqrsMapperService.toWordEvent(message);
            LOGGER.info("Kafka word event listener. Message was received and parsed. {}", wordEvent);

            commandHandler.handleWordEvent(wordEvent);
        } catch (Throwable e) {
            LOGGER.info("");
        }
    }

    @KafkaListener(topics = "dictionaryEventTopic", groupId = "dictionaryConsumerEventsGroup")
    public void listenDictionaryEvent(final String message) throws IOException {
        final DictionaryEvent dictionaryEvent = cqrsMapperService.toDictionaryEvent(message);
        commandHandler.handleDictionaryEvent(dictionaryEvent);
    }
}
