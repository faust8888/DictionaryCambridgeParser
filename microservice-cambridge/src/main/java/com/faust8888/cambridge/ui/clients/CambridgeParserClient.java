package com.faust8888.cambridge.ui.clients;


import com.faust8888.cambridge.ui.clients.item.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource(value={"classpath:application.properties"})
public class CambridgeParserClient {

    private RestTemplate restTemplate;
    private String cambridgeParserHost;
    private String cambridgeParserPort;

    @Autowired
    public CambridgeParserClient(@Value("${cambridge.parser.service.host}") String cambridgeParserHost,
                                 @Value("${cambridge.parser.service.port}") String cambridgeParserPort) {
        restTemplate = new RestTemplate();
        this.cambridgeParserHost = cambridgeParserHost;
        this.cambridgeParserPort = cambridgeParserPort;
    }

    public Word getWord(final String word) {

        return restTemplate.getForObject(getCambridgeParserUrl() + word, Word.class);
    }

    private String getCambridgeParserUrl() {
        return String.format("http://%s:%s/cambridgeService/parser/", cambridgeParserHost, cambridgeParserPort);
    }

}