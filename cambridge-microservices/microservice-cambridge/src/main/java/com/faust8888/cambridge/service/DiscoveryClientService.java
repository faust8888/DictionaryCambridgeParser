package com.faust8888.cambridge.service;

import com.faust8888.cambridge.spring.DictionaryAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscoveryClientService {

    private DiscoveryClient discoveryClient;
    private DictionaryAppConfig dictionaryConfig;

    @Autowired
    public DiscoveryClientService(DiscoveryClient discoveryClient, DictionaryAppConfig dictionaryConfig) {
        this.discoveryClient = discoveryClient;
        this.dictionaryConfig = dictionaryConfig;
    }

    public String getAddedWordEventUrl() {
        ServiceInstance serviceInstance = getServerInstance("cambridgeevents");
        String host = serviceInstance.getHost();
        Integer port = serviceInstance.getPort();

        return String.format("http://%s:%s/cambridge/events/addWordEvent", host, port);
    }

    private ServiceInstance getServerInstance(final String serviceName) {
        List<ServiceInstance> instances =
                discoveryClient.getInstances("cambridgeevents");
        if(instances == null || instances.size() == 0) {
            throw new RuntimeException(String.format("Service %s was not registred at Eureka!", serviceName));
        }
        return instances.get(0);
    }

}
