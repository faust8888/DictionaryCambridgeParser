package com.faust8888.cambridge.controllers;

import com.faust8888.cambridge.clients.CambridgeEventClient;
import com.faust8888.cambridge.items.words.Word;
import com.faust8888.cambridge.service.CambridgePageParserService;
import com.faust8888.cambridge.spring.DictionaryAppConfig;
import com.faust8888.cambridge.events.WordAddedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/cambridge/v1")
public class CambridgeController {

    private CambridgeEventClient eventClient;
    private CambridgePageParserService pageParserService;
    private DictionaryAppConfig config;

    @Autowired
    public CambridgeController(DictionaryAppConfig config, CambridgePageParserService pageParserService, CambridgeEventClient eventClient) {
        this.eventClient = eventClient;
        this.pageParserService = pageParserService;
        this.config = config;
    }

    @RequestMapping(value = "/findWord/{word}", method = RequestMethod.GET)
    public Word findWordRequest(@PathVariable("word") String word) {
        try {
            return pageParserService.parse(word, 1000);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/addWord/{word}", method = RequestMethod.GET)
    public ResponseEntity addWordRequest(@PathVariable("word") String word) {
        try {
            Word newWord = pageParserService.parse(word, 2000);
            WordAddedEvent wordAddedEvent = new WordAddedEvent.Builder()
                    .addEventId(1L)
                    .addDictionaryId(1L)
                    .addDictionaryName("dictionaryName")
                    .addWord(newWord)
                    .build();
            eventClient.createWordAddedEvent(wordAddedEvent);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity(HttpStatus.REQUEST_TIMEOUT);
        }
    }

}
