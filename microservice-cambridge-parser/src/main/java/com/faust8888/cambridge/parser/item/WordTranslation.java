package com.faust8888.cambridge.parser.item;

import java.util.List;

public class WordTranslation {

    private final String word;
    private final String shortMeaning;
    private final String form;
    private final List<WordMeaning> examples;

    public WordTranslation(String word, String shortMeaning, String form, List<WordMeaning> examples) {
        this.word = word;
        this.shortMeaning = shortMeaning;
        this.form = form;
        this.examples = examples;
    }

    public String getWord() {
        return word;
    }

    public String getShortMeaning() {
        return shortMeaning;
    }

    public String getForm() {
        return form;
    }

    public List<WordMeaning> getExamples() {
        return examples;
    }

    @Override
    public String toString() {
        return "WordTranslation{" +
                "word='" + word + '\'' +
                ", shortMeaning='" + shortMeaning + '\'' +
                ", form='" + form + '\'' +
                ", examples=" + examples +
                '}';
    }
}

