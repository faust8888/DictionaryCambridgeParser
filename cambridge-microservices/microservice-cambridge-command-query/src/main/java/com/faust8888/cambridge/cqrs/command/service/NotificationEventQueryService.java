package com.faust8888.cambridge.cqrs.command.service;

import com.faust8888.cambridge.cqrs.query.QueryEventReceiverService;
import com.faust8888.cambridge.events.WordEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationEventQueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationEventQueryService.class);

    private QueryEventReceiverService queryEventReceiverService;

    @Autowired
    public NotificationEventQueryService(final QueryEventReceiverService queryEventReceiverService) {
        this.queryEventReceiverService = queryEventReceiverService;
    }


    public void notifyAboutAddedWordEvent(final WordEvent wordEvent) {
        LOGGER.info("Notify Elasticsearch about WordEvent. UID - {}, Event - {}, Word - {}",
                wordEvent.getEventUUID(), wordEvent.getKindEventEnum().toValue(), wordEvent.getWordAsString());

        queryEventReceiverService.saveWord(wordEvent);
    }

}
