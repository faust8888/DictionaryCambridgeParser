package com.faust8888.cambridge.events.controllers;

import com.faust8888.cambridge.events.kafka.KafkaCambridgeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cambridge/events")
public class CambridgeEventController {

    private KafkaCambridgeEventService kafkaCambridgeEventService;

    @Autowired
    public CambridgeEventController(final KafkaCambridgeEventService kafkaCambridgeEventService) {
        this.kafkaCambridgeEventService = kafkaCambridgeEventService;
    }

    @RequestMapping(value = "/addWordEvent", method = RequestMethod.PUT)
    public ResponseEntity addWordEvent(@RequestBody final String addWordEvent) {
        kafkaCambridgeEventService.sendWordEventMessage(addWordEvent);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/addDictionaryEvent", method = RequestMethod.PUT)
    public ResponseEntity addDictionaryEvent(@RequestBody final String addDictionaryEvent) {
        kafkaCambridgeEventService.sendDictionaryEventMessage(addDictionaryEvent);
        return new ResponseEntity(HttpStatus.OK);
    }
}
