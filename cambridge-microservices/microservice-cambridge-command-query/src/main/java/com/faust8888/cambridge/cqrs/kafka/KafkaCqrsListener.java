package com.faust8888.cambridge.cqrs.kafka;

import com.faust8888.cambridge.cqrs.command.service.CommandHandlerService;
import com.faust8888.cambridge.cqrs.command.service.CqrsMapperService;
import com.faust8888.cambridge.cqrs.exceptions.command.EventValidationMessageException;
import com.faust8888.cambridge.events.CambridgeEvent;
import com.faust8888.cambridge.events.DictionaryEvent;
import com.faust8888.cambridge.events.WordEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

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
    public void listenWordEvent(final String message) {
        try {
            final WordEvent wordEvent = cqrsMapperService.toWordEvent(message);
            LOGGER.info("Kafka word event listener. Message was received and parsed. {}", wordEvent);
            checkEvent(wordEvent);

            commandHandler.handleWordEvent(wordEvent);
        } catch (Exception e) {
            LOGGER.error("Exception has occurred during a word event handling of message from Kafka.", e);
        }
    }

    @KafkaListener(topics = "dictionaryEventTopic", groupId = "dictionaryConsumerEventsGroup")
    public void listenDictionaryEvent(final String message) {
        try {
            final DictionaryEvent dictionaryEvent = cqrsMapperService.toDictionaryEvent(message);
            LOGGER.info("Kafka dictionary event listener. Message was received and parsed. {}", dictionaryEvent);
            checkEvent(dictionaryEvent);

            commandHandler.handleDictionaryEvent(dictionaryEvent);
        }catch (Exception e) {
            LOGGER.error("Exception has occurred during a dictionary event handling of message from Kafka.", e);
        }
    }

    private void checkEvent(final CambridgeEvent event) throws EventValidationMessageException {
        try {
            Objects.requireNonNull(event.getEventUUID(), "UUID parameter is required and can't be null.");
            if(event instanceof WordEvent) {
                WordEvent wordEvent = (WordEvent)event;
                Objects.requireNonNull(wordEvent.getWord(), "Word parameter is required and can't be null.");
                Objects.requireNonNull(wordEvent.getKindEventEnum(), "KindEventEnum parameter is required and can't be null");
            } else if (event instanceof DictionaryEvent) {
                DictionaryEvent dictionaryEvent = (DictionaryEvent)event;
                Objects.requireNonNull(dictionaryEvent.getDictionaryName(), "DictionaryName parameter is required and can't be null.");
            }
        } catch (NullPointerException npe) {
            throw new EventValidationMessageException("Event's params validation didn't succeed: " + npe.getMessage(), npe);
        }
    }
}
