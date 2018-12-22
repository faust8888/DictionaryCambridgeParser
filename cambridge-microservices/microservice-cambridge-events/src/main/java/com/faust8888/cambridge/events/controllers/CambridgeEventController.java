package com.faust8888.cambridge.events.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cambridge/events")
public class CambridgeEventController {

    @RequestMapping(value = "/addWordEvent", method = RequestMethod.PUT)
    public ResponseEntity addWordEvent(@RequestBody String addWordEvent) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
