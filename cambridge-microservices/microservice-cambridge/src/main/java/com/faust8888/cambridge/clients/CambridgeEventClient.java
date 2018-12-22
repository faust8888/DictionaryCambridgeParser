package com.faust8888.cambridge.clients;

import com.faust8888.cambridge.events.WordAddedEvent;
import com.faust8888.cambridge.service.DiscoveryClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CambridgeEventClient {

    private RestTemplate customRestTemplate;
    private DiscoveryClientService discoveryClient;

    @Autowired
    public CambridgeEventClient(DiscoveryClientService discoveryClient, RestTemplate customRestTemplate) {
        this.customRestTemplate = customRestTemplate;
        this.discoveryClient = discoveryClient;
    }

    @HystrixCommand(
            commandProperties= {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="6000"),
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public void createWordAddedEvent(final WordAddedEvent wordAddedEvent) {
        customRestTemplate.put(discoveryClient.getAddedWordEventUrl(), wordAddedEvent.toStringJSON());
    }

//
//    private Word buildFallbackWord(final String word) {
//        Word fallBackWord = new Word(word + "(fallback)", Collections.emptyList());
//        return fallBackWord;
//    }

}