package com.faust8888.cambridge.cqrs.command.service;

import com.faust8888.cambridge.cqrs.command.model.Dictionary;
import com.faust8888.cambridge.cqrs.command.model.Word;
import com.faust8888.cambridge.events.DictionaryAddedEvent;
import com.faust8888.cambridge.events.WordAddedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandlerService {

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

    public void handleAddWordEvent(final WordAddedEvent wordAddedEvent) {
        Dictionary dictionary = repositoryService.getDictionaryById(wordAddedEvent.getDictionaryId());
        Word word = mapperService.toWord(wordAddedEvent);
        repositoryService.saveWord(word, dictionary);
    }

    public void handleAddDictionaryEvent(final DictionaryAddedEvent dictionaryAddedEvent) {
        Dictionary dictionary = mapperService.toDictionary(dictionaryAddedEvent);
        repositoryService.saveDictionary(dictionary);
    }
}
