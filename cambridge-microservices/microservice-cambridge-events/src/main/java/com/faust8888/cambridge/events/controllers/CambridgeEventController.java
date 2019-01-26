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

    @RequestMapping(value = "/wordEvent", method = RequestMethod.PUT)
    public ResponseEntity wordEventRequest(@RequestBody final String wordEvent) {
        kafkaCambridgeEventService.sendWordEventMessage(wordEvent);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/dictionaryEvent", method = RequestMethod.PUT)
    public ResponseEntity dictionaryEventRequest(@RequestBody final String dictionaryEvent) {
        kafkaCambridgeEventService.sendDictionaryEventMessage(dictionaryEvent);
        return new ResponseEntity(HttpStatus.OK);
    }
}
