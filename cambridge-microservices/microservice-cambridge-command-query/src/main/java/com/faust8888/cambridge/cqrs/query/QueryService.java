package com.faust8888.cambridge.cqrs.query;

import com.faust8888.cambridge.cqrs.query.elasticsearch.ElasticsearchHelper;
import com.faust8888.cambridge.cqrs.query.elasticsearch.connection.factory.TransportClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

    private TransportClientFactory transportClientFactory;

    @Autowired
    public QueryService(final TransportClientFactory transportClientFactory) {
        this.transportClientFactory = transportClientFactory;
    }

    public void getWordById(final String wordId) throws Exception {
        transportClientFactory
                .getTransportClient()
                .prepareGet(ElasticsearchHelper.DICTIONARY_INDEX, ElasticsearchHelper.DICTIONARY_TYPE, wordId)
                .get();
    }

}
