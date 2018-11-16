package com.faust8888.cambridge.logic;

import com.faust8888.cambridge.ui.clients.CambridgeParserClient;
import com.faust8888.cambridge.ui.clients.item.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CambridgeController {

    private CambridgeParserClient cambridgeParserClient;

    @Autowired
    public CambridgeController(CambridgeParserClient cambridgeParserClient) {
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
