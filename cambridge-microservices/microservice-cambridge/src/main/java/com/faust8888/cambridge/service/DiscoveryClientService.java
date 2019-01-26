package com.faust8888.cambridge.service;

import com.faust8888.cambridge.spring.CambridgeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoveryClientService {

    private DiscoveryClient discoveryClient;
    private CambridgeConfig dictionaryConfig;

    @Autowired
    public DiscoveryClientService(final DiscoveryClient discoveryClient, final CambridgeConfig dictionaryConfig) {
        this.discoveryClient = discoveryClient;
        this.dictionaryConfig = dictionaryConfig;
    }

    public String getWordEventUrl() {
        ServiceInfo eventServiceInfo = getEventServiceInfo();
        return String.format("http://%s:%s/cambridge/events/wordEvent",
                eventServiceInfo.getHost(), eventServiceInfo.getPort());
    }

    public String getAddedDictionaryEventUrl() {
        ServiceInfo eventServiceInfo = getEventServiceInfo();
        return String.format("http://%s:%s/cambridge/events/dictionaryEvent",
                eventServiceInfo.getHost(), eventServiceInfo.getPort());
    }

    private ServiceInfo getEventServiceInfo() {
        ServiceInstance eventServiceInstance = getServerInstance("cambridgeevents");
        String host = eventServiceInstance.getHost();
        Integer port = eventServiceInstance.getPort();
        return new ServiceInfo(host, port);
    }

    private ServiceInstance getServerInstance(final String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if(instances == null || instances.size() == 0) {
            throw new RuntimeException(String.format("Service %s was not registred at Eureka!", serviceName));
        }
        return instances.get(0);
    }

    private static final class ServiceInfo {

        private final String host;

        private final Integer port;

        public ServiceInfo(final String host, final Integer port) {
            this.host = host;
            this.port = port;
        }

        public String getHost() {
            return host;
        }

        public Integer getPort() {
            return port;
        }
    }
}
