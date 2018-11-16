package com.faust8888.cambridge.parser.item;

import java.util.List;

public class Word {

    private final String word;
    private final List<WordTranslation> wordTranslations;

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
