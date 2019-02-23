package com.faust8888.cambridge.cqrs.query.elasticsearch;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.util.function.Function;

public class ElasticsearchHelper {

    public static final String DICTIONARY_INDEX = "dictionary";

    public static final String DICTIONARY_TYPE = "dictionary_type";

    private static final Function<String, GetRequest> IS_WORD_EXIST_REQUEST = (word ->
    {
        GetRequest request = new GetRequest(DICTIONARY_INDEX, DICTIONARY_TYPE, word);
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        return request;
    });

    public static GetRequest createIsWordExistRequest(final String word) {
        return IS_WORD_EXIST_REQUEST.apply(word);
    }
}
