package com.faust8888.pdf.parser.web;

import com.faust8888.pdf.parser.servcie.PdfParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class PdfParserController {

    private PdfParserService pdfParserService;

    @Autowired
    public PdfParserController(PdfParserService pdfParserService) {
        this.pdfParserService = pdfParserService;
    }


}
