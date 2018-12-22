package com.faust8888.pdf.parser.servcie;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class PdfParserService {

    private static final String PDF_FILE_NAME = "War_and_Peace.pdf";

    private PdfParser pdfParser = new PdfParser();

    private File getPdfFile() {
        try {
            URL fileUrl = this.getClass().getClassLoader().getResource(PDF_FILE_NAME);
            File pdfFile = new File(fileUrl.toURI());
            return pdfFile;
        } catch (Throwable e) {
            System.out.println(String.format("Can'readPdf get a pdf file: %s", e.getMessage()));
            return null;
        }
    }

    private PDDocument getPdfDocument() throws IOException {
        return PDDocument.load(getPdfFile());
    }

    private byte[] getTextAsBytesArray() throws IOException {
        return pdfParser.parse(getPdfDocument(), 500);
    }

    public void readPdf() throws IOException {
        System.out.println(new String(getTextAsBytesArray()));
    }

}
