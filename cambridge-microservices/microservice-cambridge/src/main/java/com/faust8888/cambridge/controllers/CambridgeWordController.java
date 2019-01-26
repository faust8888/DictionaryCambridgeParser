package com.faust8888.cambridge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faust8888.cambridge.clients.CambridgeEventClient;
import com.faust8888.cambridge.items.words.Word;
import com.faust8888.cambridge.service.CambridgePageParserService;
import com.faust8888.cambridge.spring.CambridgeConfig;
import com.faust8888.cambridge.events.WordAddedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(value = "/cambridge/v1")
public class CambridgeWordController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private CambridgeEventClient eventClient;
    private CambridgePageParserService pageParserService;
    private CambridgeConfig config;

    @Autowired
    public CambridgeWordController(final CambridgeConfig config,
                                   final CambridgePageParserService pageParserService,
                                   final CambridgeEventClient eventClient) {
        this.eventClient = eventClient;
        this.pageParserService = pageParserService;
        this.config = config;
    }

    @RequestMapping(value = "/findWord/{word}", method = RequestMethod.GET)
    public Word findWordRequest(@PathVariable("word") final String word) {
        try {
            return pageParserService.parse(word, 1000);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/addWord/", method = RequestMethod.PUT)
    public ResponseEntity addWordRequest(@RequestBody final String event) {
        try {
            WordAddedEvent wordAddedEvent = OBJECT_MAPPER.readValue(event, WordAddedEvent.class);
            Word newWord = pageParserService.parse(wordAddedEvent.getWordSearch(), 2000);
            wordAddedEvent.setWord(newWord);
            eventClient.createWordAddedEvent(wordAddedEvent);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity(HttpStatus.REQUEST_TIMEOUT);
        }
    }
}
