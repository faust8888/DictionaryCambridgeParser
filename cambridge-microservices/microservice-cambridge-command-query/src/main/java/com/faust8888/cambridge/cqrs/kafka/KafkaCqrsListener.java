package com.faust8888.cambridge.cqrs.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faust8888.cambridge.cqrs.command.service.CommandHandlerService;
import com.faust8888.cambridge.events.DictionaryAddedEvent;
import com.faust8888.cambridge.events.WordAddedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class KafkaCqrsListener {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private CommandHandlerService commandHandlerService;

    @Autowired
    public KafkaCqrsListener(CommandHandlerService commandHandlerService) {
        this.commandHandlerService = commandHandlerService;
    }

    @KafkaListener(topics = "wordEventTopic", groupId = "consumerEventsGroup")
    public void listenAddWordInDictionaryEvent(String message) throws IOException {
        WordAddedEvent wordAddedEvent = OBJECT_MAPPER.readValue(message, WordAddedEvent.class);
        commandHandlerService.handleAddWordEvent(wordAddedEvent);
    }

    @KafkaListener(topics = "dictionaryEventTopic", groupId = "dictionaryConsumerEventsGroup")
    public void listenAddDictionaryEvent(String message) throws IOException {
        DictionaryAddedEvent dictionaryAddedEvent = OBJECT_MAPPER.readValue(message, DictionaryAddedEvent.class);
        commandHandlerService.handleAddDictionaryEvent(dictionaryAddedEvent);
    }

}
