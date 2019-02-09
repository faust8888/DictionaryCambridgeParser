package com.faust8888.cambridge.cqrs.command.service;

import com.faust8888.cambridge.cqrs.command.model.Dictionary;
import com.faust8888.cambridge.cqrs.command.model.Word;
import com.faust8888.cambridge.events.DictionaryEvent;
import com.faust8888.cambridge.events.WordEvent;
import com.faust8888.cambridge.items.words.WordKindEventEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHandlerService.class);

    private CommonCommandRepositoryService repositoryService;
    private NotificationEventQueryService notificationEventQueryService;
    private CqrsMapperService mapperService;

    @Autowired
    public CommandHandlerService(
            final CommonCommandRepositoryService repositoryService,
            final NotificationEventQueryService notificationEventQueryService,
            final CqrsMapperService mapperService) {
        this.repositoryService = repositoryService;
        this.notificationEventQueryService = notificationEventQueryService;
        this.mapperService = mapperService;
    }

    public void handleWordEvent(final WordEvent wordEvent) {
        LOGGER.info("Start handle word event. UID - {}, Event - {}, Word - {}",
                wordEvent.getEventUUID(), wordEvent.getKindEventEnum().toValue(), wordEvent.getWordAsString());

        final Dictionary dictionary = repositoryService.getDictionaryById(wordEvent.getDictionaryId());
        if(wordEvent.getKindEventEnum() == WordKindEventEnum.ADD) {
            final Word word = mapperService.toWord(wordEvent);
            repositoryService.saveWord(word, dictionary);
            notificationEventQueryService.notifyAboutAddedWordEvent(wordEvent);
        } else if(wordEvent.getKindEventEnum() == WordKindEventEnum.DELETE) {
            final Word word = repositoryService.findWordByWord(wordEvent.getWordAsString());
            repositoryService.deleteWord(word, dictionary);
        }

        LOGGER.info("Finish handle word event. UID - {}", wordEvent.getEventUUID());
    }

    public void handleDictionaryEvent(final DictionaryEvent dictionaryEvent) {
        final Dictionary dictionary = mapperService.toDictionary(dictionaryEvent);
        repositoryService.saveDictionary(dictionary);
    }
}
