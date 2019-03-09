package com.faust8888.cambridge.cqrs.command.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faust8888.cambridge.cqrs.command.model.Dictionary;
import com.faust8888.cambridge.cqrs.command.model.Meaning;
import com.faust8888.cambridge.cqrs.command.model.Translation;
import com.faust8888.cambridge.cqrs.command.model.Word;
import com.faust8888.cambridge.events.CambridgeEvent;
import com.faust8888.cambridge.events.DictionaryEvent;
import com.faust8888.cambridge.events.WordEvent;
import com.faust8888.cambridge.items.words.WordMeaning;
import com.faust8888.cambridge.items.words.WordTranslation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CqrsMapperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CqrsMapperService.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public Dictionary toDictionary(final DictionaryEvent event) {
        final Dictionary dictionary = new Dictionary();
        dictionary.setTag(event.getTag());
        dictionary.setDictionaryName(event.getDictionaryName());
        dictionary.setCreateDate(event.getCreateDate());

        return dictionary;
    }

    public Word toWord(final WordEvent event) {
        LOGGER.info("Convert WordEvent into Word entity. UID - {}, word - {}", event.getEventUUID(), event.getWordAsString());

        final Word word = new Word();
        final List<WordTranslation> wordTranslations = event.getWord().getWordTranslations();
        final List<Translation> translations = new ArrayList<>();

        for(WordTranslation wordTranslation: wordTranslations) {
            final Translation translation = new Translation();
            translation.setForm(wordTranslation.getForm());
            translation.setShortMeaning(wordTranslation.getShortMeaning());
            translation.setWord(word);

            final List<WordMeaning> wordMeanings = wordTranslation.getExamples();
            final List<Meaning> meanings = new ArrayList<>();

            for(WordMeaning wordMeaning: wordMeanings) {
                final Meaning meaning = new Meaning();
                meaning.setWord(word);
                meaning.setExplanation(wordMeaning.getExplanation());
                meaning.setTranslation(translation);
                meaning.setExamples(String.join(",", wordMeaning.getExamples()));
                meanings.add(meaning);
            }
            translation.setExamples(meanings);
            translations.add(translation);
        }

        word.setTranslations(translations);
        word.setWord(event.getWord().getWord());

        return word;
    }

    public WordEvent toWordEvent(final String wordEventAsJson) throws IOException {
        return OBJECT_MAPPER.readValue(wordEventAsJson, WordEvent.class);
    }

    public DictionaryEvent toDictionaryEvent(final String dictionaryEventAsString) throws IOException {
        return OBJECT_MAPPER.readValue(dictionaryEventAsString, DictionaryEvent.class);
    }

}
