package com.faust8888.cambridge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faust8888.cambridge.clients.CambridgeEventClient;
import com.faust8888.cambridge.events.DictionaryAddedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CambridgeDictionaryController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private CambridgeEventClient eventClient;

    @Autowired
    public CambridgeDictionaryController(CambridgeEventClient eventClient) {
        this.eventClient = eventClient;
    }

    @RequestMapping(value = "/addDictionary/{dictionary}", method = RequestMethod.GET)
    public ResponseEntity addDictionaryRequest(@PathVariable("dictionary") String dictionary) {
        try {
            DictionaryAddedEvent dictionaryAddedEvent = OBJECT_MAPPER.readValue(dictionary, DictionaryAddedEvent.class);
            eventClient.createDictionaryAddedEvent(dictionaryAddedEvent);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity(HttpStatus.REQUEST_TIMEOUT);
        }
    }

}
