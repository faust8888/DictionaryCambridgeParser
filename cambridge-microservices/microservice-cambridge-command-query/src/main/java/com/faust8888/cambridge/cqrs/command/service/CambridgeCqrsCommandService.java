package com.faust8888.cambridge.cqrs.command.service;

import com.faust8888.cambridge.cqrs.command.model.Dictionary;
import com.faust8888.cambridge.cqrs.command.repository.DictionaryCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CambridgeCqrsCommandService {

    private DictionaryCommandRepository dictionaryCommandRepository;

    @Autowired
    public CambridgeCqrsCommandService(DictionaryCommandRepository dictionaryCommandRepository) {
        this.dictionaryCommandRepository = dictionaryCommandRepository;
    }

    public void saveDictionary(Dictionary dictionary) {
        dictionaryCommandRepository.save(dictionary);
    }

}
