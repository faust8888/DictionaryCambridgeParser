package com.faust8888.cambridge.clients;

import com.faust8888.cambridge.events.DictionaryEvent;
import com.faust8888.cambridge.events.WordEvent;
import com.faust8888.cambridge.service.DiscoveryClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CambridgeEventClient {

    private RestTemplate customRestTemplate;
    private DiscoveryClientService discoveryClient;

    @Autowired
    public CambridgeEventClient(final DiscoveryClientService discoveryClient, final RestTemplate customRestTemplate) {
        this.customRestTemplate = customRestTemplate;
        this.discoveryClient = discoveryClient;
    }

    @HystrixCommand(
            commandProperties= {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="6000"),
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public void createWordAddedEvent(final WordEvent wordEvent) {
        customRestTemplate.put(discoveryClient.getWordEventUrl(), wordEvent.toStringJSON());
    }

    @HystrixCommand(
            commandProperties= {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="6000"),
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public void createDictionaryAddedEvent(final DictionaryEvent dictionaryEvent) {
        customRestTemplate.put(discoveryClient.getAddedDictionaryEventUrl(), dictionaryEvent.toStringJSON());
    }

    @HystrixCommand(
            commandProperties= {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="6000"),
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public void createWordDeletedEvent(final WordEvent wordEvent) {
        customRestTemplate.put(discoveryClient.getWordEventUrl(), wordEvent.toStringJSON());
    }

//
//    private Word buildFallbackWord(final String word) {
//        Word fallBackWord = new Word(word + "(fallback)", Collections.emptyList());
//        return fallBackWord;
//    }

}