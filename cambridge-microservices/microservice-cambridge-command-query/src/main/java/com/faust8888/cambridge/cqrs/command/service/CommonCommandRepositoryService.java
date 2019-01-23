package com.faust8888.cambridge.cqrs.command.service;

import com.faust8888.cambridge.cqrs.command.model.Dictionary;
import com.faust8888.cambridge.cqrs.command.model.Word;
import com.faust8888.cambridge.cqrs.command.repository.DictionaryCommandRepository;
import com.faust8888.cambridge.cqrs.command.repository.DictionaryContentCommandRepository;
import com.faust8888.cambridge.cqrs.command.repository.WordCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommonCommandRepositoryService {

    private DictionaryCommandRepository dictionaryCommandRepository;
    private DictionaryContentCommandRepository dictionaryContentCommandRepository;
    private WordCommandRepository wordCommandRepository;

    @Autowired
    public CommonCommandRepositoryService(
            DictionaryCommandRepository dictionaryCommandRepository,
            DictionaryContentCommandRepository dictionaryContentCommandRepository,
            WordCommandRepository wordCommandRepository) {
        this.dictionaryCommandRepository = dictionaryCommandRepository;
        this.dictionaryContentCommandRepository = dictionaryContentCommandRepository;
        this.wordCommandRepository = wordCommandRepository;
    }

    public void saveDictionary(Dictionary dictionary) {
        dictionaryCommandRepository.save(dictionary);
    }

    public void updateDictionary(Dictionary dictionary) {

    }

    public void deleteDictionary(Dictionary dictionary) {
        dictionaryCommandRepository.delete(dictionary);
    }

    public void saveWord(Word word, Long dictionaryId) {

    }

    public void deleteWord(Word word, Long dictionaryId) {

    }



}
