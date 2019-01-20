package com.faust8888.cambridge.cqrs.kafka;

import com.faust8888.cambridge.cqrs.command.service.CambridgeCqrsCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class KafkaCqrsListener {

    private CambridgeCqrsCommandService cambridgeCqrsCommandService;

    @Autowired
    public KafkaCqrsListener(CambridgeCqrsCommandService cambridgeCqrsCommandService) {
        this.cambridgeCqrsCommandService = cambridgeCqrsCommandService;
    }

    @KafkaListener(topics = "eventTopic", groupId = "consumerEventsGroup")
    public void listen(String message) {
        System.out.println("Received Messasge in group foo: " + message);
//        cambridgeCqrsCommandService.saveDictionary(dictionary);
    }

}
