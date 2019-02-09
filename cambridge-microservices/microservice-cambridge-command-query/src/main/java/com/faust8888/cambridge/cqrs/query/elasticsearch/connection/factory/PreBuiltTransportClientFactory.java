package com.faust8888.cambridge.cqrs.query.elasticsearch.connection.factory;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

@Component
public class PreBuiltTransportClientFactory {

    public PreBuiltTransportClient getPreBuiltTransportClient(final Settings settings) {
        return new PreBuiltTransportClient(settings);
    }

}
