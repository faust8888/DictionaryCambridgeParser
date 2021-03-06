package com.faust8888.cambridge.cqrs.command.service;

import com.faust8888.cambridge.cqrs.command.model.Dictionary;
import com.faust8888.cambridge.cqrs.command.model.Word;
import com.faust8888.cambridge.cqrs.command.repository.DictionaryCommandRepository;
import com.faust8888.cambridge.cqrs.command.repository.DictionaryContentCommandRepository;
import com.faust8888.cambridge.cqrs.command.repository.WordCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class CommonCommandRepositoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonCommandRepositoryService.class);

    private DictionaryCommandRepository dictionaryCommandRepository;
    private DictionaryContentCommandRepository dictionaryContentCommandRepository;
    private WordCommandRepository wordCommandRepository;

    @Autowired
    public CommonCommandRepositoryService(
            final DictionaryCommandRepository dictionaryCommandRepository,
            final DictionaryContentCommandRepository dictionaryContentCommandRepository,
            final WordCommandRepository wordCommandRepository) {
        this.dictionaryCommandRepository = dictionaryCommandRepository;
        this.dictionaryContentCommandRepository = dictionaryContentCommandRepository;
        this.wordCommandRepository = wordCommandRepository;
    }

    public Dictionary getDictionaryById(final Long id) throws EntityNotFoundException {
        return dictionaryCommandRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Couldn't find a dictionary with id " + id));
    }

    public void saveDictionary(final Dictionary dictionary) {
        dictionaryCommandRepository.save(dictionary);
    }

    public void updateDictionary(final Dictionary dictionary) {

    }

    public void deleteDictionary(final Dictionary dictionary) {
        dictionaryCommandRepository.delete(dictionary);
    }

    public void saveWord(final Word word, final Dictionary dictionary) {
        LOGGER.info("Save word {} into the database. Dictionary: id - {}, name - {}",
                word.getWord(), dictionary.getId(), dictionary.getDictionaryName());

        dictionary.addWord(word);
        dictionaryCommandRepository.save(dictionary);
    }

    public void deleteWord(final Word word, final Dictionary dictionary) {
        dictionary.deleteWord(word);
        dictionaryCommandRepository.save(dictionary);
    }

    public Word findWordByWord(final String word){
        return wordCommandRepository.findWordByWord(word);
    }
}
