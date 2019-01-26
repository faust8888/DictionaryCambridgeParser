package com.faust8888.cambridge.cqrs.command.service;

import com.faust8888.cambridge.cqrs.command.model.Dictionary;
import com.faust8888.cambridge.cqrs.command.model.Meaning;
import com.faust8888.cambridge.cqrs.command.model.Translation;
import com.faust8888.cambridge.cqrs.command.model.Word;
import com.faust8888.cambridge.events.DictionaryAddedEvent;
import com.faust8888.cambridge.events.WordAddedEvent;
import com.faust8888.cambridge.items.words.WordMeaning;
import com.faust8888.cambridge.items.words.WordTranslation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CqrsMapperService {

    public Dictionary toDictionary(final DictionaryAddedEvent event) {
        Dictionary dictionary = new Dictionary();
        dictionary.setTag(event.getTag());
        dictionary.setDictionaryName(event.getDictionaryName());
        dictionary.setCreateDate(event.getCreateDate());

        return dictionary;
    }

    public Word toWord(final WordAddedEvent event) {
        Word word = new Word();

        List<WordTranslation> wordTranslations = event.getWord().getWordTranslations();
        List<Translation> translations = new ArrayList<>();

        for(WordTranslation wordTranslation: wordTranslations) {
            Translation translation = new Translation();
            translation.setForm(wordTranslation.getForm());
            translation.setShortMeaning(wordTranslation.getShortMeaning());
            translation.setWord(word);

            List<WordMeaning> wordMeanings = wordTranslation.getExamples();
            List<Meaning> meanings = new ArrayList<>();

            for(WordMeaning wordMeaning: wordMeanings) {
                Meaning meaning = new Meaning();
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

}
