package com.faust8888.cambridge.cqrs.query.elasticsearch.connection.factory;

import com.faust8888.cambridge.cqrs.command.config.ConnectionConfig;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@CacheConfig(cacheNames = "elasticsearch-connection")
public class TransportClientFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransportClientFactory.class);

    private PreBuiltTransportClientFactory preBuiltTransportClientFactory;
    private ConnectionConfig connectionConfig;

    @Autowired
    public TransportClientFactory(
            final PreBuiltTransportClientFactory preBuiltTransportClientFactory,
            final ConnectionConfig connectionConfig) {
        this.preBuiltTransportClientFactory = preBuiltTransportClientFactory;
        this.connectionConfig = connectionConfig;
    }

    @Cacheable
    public TransportClient getTransportClient() throws Exception {
        LOGGER.debug("Obtaining a TransportClient for Elasticsearch. Cluster name - {}", connectionConfig.getElasticSearchClusterName());

        final Settings settings = Settings.builder()
                .put("cluster.name", connectionConfig.getElasticSearchClusterName())
                .build();
        final TransportClient transportClient = preBuiltTransportClientFactory.getPreBuiltTransportClient(settings);
        TransportAddress transportAddress = getElasticsearchTransportAddress();
        transportClient.addTransportAddress(transportAddress);

        return transportClient;
    }

    private TransportAddress getElasticsearchTransportAddress() throws Exception {
        try {
            final InetAddress inetAddress = InetAddress.getByName(connectionConfig.getElasticSearchClusterIpAddress());
            return new TransportAddress(inetAddress, connectionConfig.getElasticSearchClusterPort());
        } catch (UnknownHostException e) {
            throw new Exception("Exception during an attempt to create a TransportAddress for Elasticsearch connection.", e);
        }
    }
}
