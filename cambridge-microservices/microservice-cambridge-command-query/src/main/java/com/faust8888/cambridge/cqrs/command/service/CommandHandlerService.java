package com.faust8888.cambridge.cqrs.command.service;

import com.faust8888.cambridge.cqrs.command.model.Dictionary;
import com.faust8888.cambridge.cqrs.command.model.Word;
import com.faust8888.cambridge.events.DictionaryEvent;
import com.faust8888.cambridge.events.WordEvent;
import com.faust8888.cambridge.items.words.WordKindEventEnum;
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

    public void handleWordEvent(final WordEvent wordEvent) {
        Dictionary dictionary = repositoryService.getDictionaryById(wordEvent.getDictionaryId());

        if(wordEvent.getKindEventEnum() == WordKindEventEnum.ADD) {
            Word word = mapperService.toWord(wordEvent);
            repositoryService.saveWord(word, dictionary);
        } else if(wordEvent.getKindEventEnum() == WordKindEventEnum.DELETE) {
            Word word = repositoryService.findWordByWord(wordEvent.getWordAsString());
            repositoryService.deleteWord(word, dictionary);
        }
    }

    public void handleDictionaryEvent(final DictionaryEvent dictionaryEvent) {
        Dictionary dictionary = mapperService.toDictionary(dictionaryEvent);
        repositoryService.saveDictionary(dictionary);
    }
}
