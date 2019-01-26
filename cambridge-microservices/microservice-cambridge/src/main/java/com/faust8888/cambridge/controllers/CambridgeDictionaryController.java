package com.faust8888.cambridge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faust8888.cambridge.clients.CambridgeEventClient;
import com.faust8888.cambridge.events.DictionaryAddedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CambridgeDictionaryController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private CambridgeEventClient eventClient;

    @Autowired
    public CambridgeDictionaryController(final CambridgeEventClient eventClient) {
        this.eventClient = eventClient;
    }

    @RequestMapping(value = "/addDictionary/", method = RequestMethod.PUT)
    public ResponseEntity addDictionaryRequest(@RequestBody final String dictionary) {
        try {
            DictionaryAddedEvent dictionaryAddedEvent = OBJECT_MAPPER.readValue(dictionary, DictionaryAddedEvent.class);
            eventClient.createDictionaryAddedEvent(dictionaryAddedEvent);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
