package com.faust8888.cambridge.ui.clients;


import com.faust8888.cambridge.ui.clients.item.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
//@PropertySource(value={"classpath:application.properties"})
public class CambridgeParserClient {

    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    public CambridgeParserClient() {
        restTemplate = new RestTemplate();
    }

    public Word getWord(final String word) {
        return restTemplate.getForObject(getCambridgeParserUrl() + word, Word.class);
    }

    private String getCambridgeParserUrl() {
        List<ServiceInstance> instances =
                discoveryClient.getInstances("cambridgeparser");

        if(instances.isEmpty()) {
            throw new RuntimeException("Service cambridgeparser was not registred at Eureka!");
        }

        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost();
        Integer port = serviceInstance.getPort();

        return String.format("http://%s:%s/parser/v1/parse/", host, port);
    }

}