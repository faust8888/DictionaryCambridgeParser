package com.faust8888.cambridge.cqrs.query;

import com.faust8888.cambridge.cqrs.query.elasticsearch.connection.factory.TransportClientFactory;
import com.faust8888.cambridge.events.WordEvent;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryEventReceiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryEventReceiverService.class);

    private static final String DICTIONARY_INDEX = "dictionary";
    private static final String DICTIONARY_TYPE = "dictionary_type";

    private TransportClientFactory transportClientFactory;

    @Autowired
    public QueryEventReceiverService(final TransportClientFactory transportClientFactory) {
        this.transportClientFactory = transportClientFactory;
    }

    public void saveWord(final WordEvent wordEvent) {
        try {
            LOGGER.info("Saving and indexing a word into Elasticsearch. UID - {}, word - {}",
                    wordEvent.getEventUUID(), wordEvent.getWordAsString());

            final IndexRequestBuilder indexRequestBuilder = transportClientFactory.getTransportClient()
                    .prepareIndex(DICTIONARY_INDEX, DICTIONARY_TYPE, wordEvent.getWordAsString());
            indexRequestBuilder.setSource(wordEvent.getWordAsString(), wordEvent.toStringJSON());
            indexRequestBuilder.execute().actionGet();
        } catch (Throwable e) {
            LOGGER.info("Exception during an attempt to save a word into Elasticsearch. UID - {}: {}",
                    wordEvent.getEventUUID(),  e.getMessage());
        }
    }

}
