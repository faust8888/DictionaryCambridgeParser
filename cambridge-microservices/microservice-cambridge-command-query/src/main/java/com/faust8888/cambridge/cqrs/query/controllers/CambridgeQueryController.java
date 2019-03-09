package com.faust8888.cambridge.cqrs.query.controllers;

import com.faust8888.cambridge.cqrs.query.QueryService;
import com.faust8888.cambridge.items.words.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CambridgeQueryController {

    private QueryService queryService;

    @Autowired
    public CambridgeQueryController (final QueryService queryService) {
        this.queryService = queryService;
    }

    @RequestMapping(value = "/getWord/{word}", method = RequestMethod.GET)
    public Word getWordRequest(@PathVariable("word") final String word) {
        try {
            if(word == null || word.isEmpty()) {
                throw new RuntimeException("Input parameter word can't be null or empty.");
            }
            return queryService.getWordById(word);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

}
