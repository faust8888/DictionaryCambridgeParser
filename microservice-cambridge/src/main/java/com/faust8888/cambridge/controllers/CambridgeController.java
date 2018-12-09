package com.faust8888.cambridge.controllers;

import com.faust8888.cambridge.spring.DictionaryAppConfig;
import com.faust8888.cambridge.clients.CambridgeParserClient;
import com.faust8888.cambridge.clients.item.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cambridge/v1")
public class CambridgeController {

    private CambridgeParserClient cambridgeParserClient;
    private DictionaryAppConfig config;

    @Autowired
    public CambridgeController(DictionaryAppConfig config, CambridgeParserClient cambridgeParserClient) {
        this.config = config;
        this.cambridgeParserClient = cambridgeParserClient;
    }

    @RequestMapping(value = "/findWord/{word}", method = RequestMethod.GET)
    public Word findWordRequest(@PathVariable("word") String word) {
        try {
            return cambridgeParserClient.getWord(word);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

}
