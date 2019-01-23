package com.faust8888.cambridge.events.controllers;

import com.faust8888.cambridge.events.kafka.KafkaCambridgeEventsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cambridge/events")
public class CambridgeEventController {

    private KafkaCambridgeEventsService kafkaCambridgeEventsService;

    public CambridgeEventController(KafkaCambridgeEventsService kafkaCambridgeEventsService) {
        this.kafkaCambridgeEventsService = kafkaCambridgeEventsService;
    }

    @RequestMapping(value = "/addWordEvent", method = RequestMethod.PUT)
    public ResponseEntity addWordEvent(@RequestBody String addWordEvent) {
        kafkaCambridgeEventsService.sendWordEventMessage(addWordEvent);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/addDictionaryEvent", method = RequestMethod.PUT)
    public ResponseEntity addDictionaryEvent(@RequestBody String addDictionaryEvent) {
        kafkaCambridgeEventsService.sendDictionaryEventMessage(addDictionaryEvent);
        return new ResponseEntity(HttpStatus.OK);
    }

}
