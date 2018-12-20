package com.faust8888.cambridge.controllers;

import com.faust8888.cambridge.service.CambridgePageParserService;
import com.faust8888.cambridge.spring.DictionaryAppConfig;
import com.faust8888.cambridge.clients.CambridgeParserClient;
import com.faust8888.cambridge.item.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/cambridge/v1")
public class CambridgeController {

    private CambridgePageParserService pageParserService;
    private DictionaryAppConfig config;

    @Autowired
    public CambridgeController(DictionaryAppConfig config, CambridgePageParserService pageParserService) {
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

}
