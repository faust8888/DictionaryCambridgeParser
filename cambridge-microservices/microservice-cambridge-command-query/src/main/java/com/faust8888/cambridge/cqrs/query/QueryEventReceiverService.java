package com.faust8888.cambridge.cqrs.query;

import com.faust8888.cambridge.cqrs.query.elasticsearch.ElasticsearchHelper;
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

    private TransportClientFactory transportClientFactory;

    @Autowired
    public QueryEventReceiverService(final TransportClientFactory transportClientFactory) {
        this.transportClientFactory = transportClientFactory;
    }

    public void saveWord(final WordEvent wordEvent) {
        try {
            if(isWordAlreadyExists(wordEvent.getWordAsString())) {
                LOGGER.info("Word {} already exists and won't be saved at the Elasticsearch. UID - {}",
                        wordEvent.getWordAsString(), wordEvent.getEventUUID());
                return;
            }

            LOGGER.info("Saving and indexing a word into Elasticsearch. UID - {}, word - {}",
                    wordEvent.getEventUUID(), wordEvent.getWordAsString());

            final IndexRequestBuilder indexRequestBuilder = createIndexRequestBuilder(wordEvent.getWordAsString());
            indexRequestBuilder.setSource(wordEvent.getWordAsString(), wordEvent.toStringJSON());
            indexRequestBuilder.execute().actionGet();
        } catch (Throwable e) {
            LOGGER.info("Exception during an attempt to save a word into Elasticsearch. UID - {}: {}",
                    wordEvent.getEventUUID(),  e.getMessage());
        }
    }

    private boolean isWordAlreadyExists(final String word) throws Exception {
        try {
            return transportClientFactory
                    .getTransportClient()
                    .get(ElasticsearchHelper.createIsWordExistRequest(word))
                    .actionGet()
                    .isExists();
        } catch (Exception e) {
            throw new Exception(String.format("Can't check that a word %s exists at the Elasticsearch or not: %s", word, e.getMessage()));
        }
    }

    private IndexRequestBuilder createIndexRequestBuilder(final String word) throws Exception {
        final IndexRequestBuilder indexRequestBuilder = transportClientFactory
                .getTransportClient()
                .prepareIndex(
                        ElasticsearchHelper.DICTIONARY_INDEX,
                        ElasticsearchHelper.DICTIONARY_TYPE,
                        word
                );
        return indexRequestBuilder;
    }

}
