package com.faust8888.cambridge.cqrs.command.service;

import com.faust8888.cambridge.events.DictionaryAddedEvent;
import com.faust8888.cambridge.events.WordAddedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandlerService {

    private CommonCommandRepositoryService repositoryService;
    private NotificationEventQueryService notificationEventQueryService;

    @Autowired
    public CommandHandlerService(
            CommonCommandRepositoryService repositoryService,
            NotificationEventQueryService notificationEventQueryService) {
        this.repositoryService = repositoryService;
        this.notificationEventQueryService = notificationEventQueryService;
    }

    public void handleAddWordEvent(WordAddedEvent wordAddedEvent) {
        //repositoryService.saveWord(wordAddedEvent.getWord(), wordAddedEvent.getDictionaryId());
    }

    public void handleAddDictionaryEvent(DictionaryAddedEvent dictionaryAddedEvent) {
        //repositoryService.saveDictionary();
    }

}
