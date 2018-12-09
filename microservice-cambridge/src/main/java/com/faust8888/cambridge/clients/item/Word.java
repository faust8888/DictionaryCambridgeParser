package com.faust8888.cambridge.clients.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class Word extends ResourceSupport {

    @JsonProperty("id")
    private String word;

    private List<WordTranslation> wordTranslations;

    public Word(){

    }

    public Word(String word, List<WordTranslation> wordTranslations) {
        this.word = word;
        this.wordTranslations = wordTranslations;
    }

    public String getWord() {
        return word;
    }

    public List<WordTranslation> getWordTranslations() {
        return wordTranslations;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", wordTranslations=" + wordTranslations +
                '}';
    }
}
