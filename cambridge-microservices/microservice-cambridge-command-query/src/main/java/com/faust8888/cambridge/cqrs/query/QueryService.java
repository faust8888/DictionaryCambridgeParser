package com.faust8888.cambridge.cqrs.query;

import com.faust8888.cambridge.cqrs.command.service.CqrsMapperService;
import com.faust8888.cambridge.cqrs.exceptions.query.NotFoundDocumentException;
import com.faust8888.cambridge.cqrs.query.elasticsearch.ElasticsearchHelper;
import com.faust8888.cambridge.cqrs.query.elasticsearch.connection.factory.TransportClientFactory;
import com.faust8888.cambridge.items.words.Word;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

    private TransportClientFactory transportClientFactory;
    private CqrsMapperService cqrsMapperService;

    @Autowired
    public QueryService(final TransportClientFactory transportClientFactory, CqrsMapperService cqrsMapperService) {
        this.transportClientFactory = transportClientFactory;
        this.cqrsMapperService = cqrsMapperService;
    }

    public Word getWordById(final String wordId) throws Exception {
        final GetResponse response = performQuery(ElasticsearchHelper.DICTIONARY_INDEX, ElasticsearchHelper.DICTIONARY_TYPE, wordId);
        final String wordEventAsJson = response.getSource().get(wordId).toString();
        return cqrsMapperService.toWordEvent(wordEventAsJson).getWord();
    }

    private GetResponse performQuery(final String index, final String type, final String id) throws Exception {
        final GetResponse response = transportClientFactory
                .getTransportClient()
                .prepareGet(index, type, id)
                .get();
        if(response == null || response.getSource() == null || response.getSource().isEmpty()) {
            String errorMessage = String.format("Can't find a document at the Elasticsearch by id - %s", id);
            throw new NotFoundDocumentException(errorMessage);
        }
        return response;
    }

}
