package com.faust8888.cambridge.parser.web;

import com.faust8888.cambridge.parser.item.Word;
import com.faust8888.cambridge.parser.service.CambridgePageParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@EnableAutoConfiguration
public class CambridgeParserController {

    @Autowired
    private CambridgePageParserService cambridgePageParserService;

    @RequestMapping(value = "/cambridgeService/parser/{word}", method = RequestMethod.GET)
    public Word word(@PathVariable("word") String word) throws IOException {
        return cambridgePageParserService.parse(word, 1000);
    }

}
