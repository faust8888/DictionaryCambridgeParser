package com.faust8888.cambridge.clients;

import com.faust8888.cambridge.clients.item.Word;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class CambridgeParserClient {

    private RestTemplate customRestTemplate;
    private DiscoveryClient discoveryClient;

    @Autowired
    public CambridgeParserClient(RestTemplate customRestTemplate, DiscoveryClient discoveryClient) {
        this.customRestTemplate = customRestTemplate;
        this.discoveryClient = discoveryClient;
    }

    @HystrixCommand(fallbackMethod = "buildFallbackWord",
            commandProperties= {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="4000"),
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public Word getWord(final String word) {
        return customRestTemplate.getForObject(getCambridgeParserUrl() + word, Word.class);
    }

    private String getCambridgeParserUrl() {
//        List<ServiceInstance> instances =
//                discoveryClient.getInstances("cambridgeparser");
//
//        if(instances.isEmpty()) {
//            throw new RuntimeException("Service cambridgeparser was not registred at Eureka!");
//        }
//
//        ServiceInstance serviceInstance = instances.get(0);
//        String host = serviceInstance.getHost();
//        Integer port = serviceInstance.getPort();
//
//        return String.format("http://%s:%s/parser/v1/parse/", host, port);
        return "http://localhost:8082/parse/";
    }

    private Word buildFallbackWord(final String word) {
        Word fallBackWord = new Word(word + "(fallback)", Collections.emptyList());
        return fallBackWord;
    }

}