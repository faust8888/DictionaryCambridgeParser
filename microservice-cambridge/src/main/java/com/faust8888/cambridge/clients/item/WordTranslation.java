package com.faust8888.cambridge.clients.item;

import java.util.List;

public class WordTranslation {

    private String word;
    private String shortMeaning;
    private String form;
    private List<WordMeaning> examples;

    public WordTranslation() {

    }

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

