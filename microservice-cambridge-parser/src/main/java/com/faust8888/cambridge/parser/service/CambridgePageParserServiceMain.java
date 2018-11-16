package com.faust8888.cambridge.parser.service;

import com.faust8888.cambridge.parser.item.Word;

import java.io.IOException;

public class CambridgePageParserServiceMain {


    public static void main(String[] args) throws IOException {
        String wordStr = "country";
        CambridgePageParserService cambridgePageParser = new CambridgePageParserService();
        Word word = cambridgePageParser.parse(wordStr,10000);
        System.out.println(word);
    }

}
