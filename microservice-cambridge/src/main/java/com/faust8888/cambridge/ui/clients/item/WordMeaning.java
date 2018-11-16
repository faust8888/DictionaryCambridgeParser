package com.faust8888.cambridge.ui.clients.item;

import java.util.List;
import java.util.Objects;

public class WordMeaning {

    private String word;
    private String explanation;
    private List<String> examples;

    public WordMeaning(){}

    public WordMeaning(String word, String explanation, List<String> examples) {
        this.word = Objects.requireNonNull(word, "WordTranslation can'readPdf be null");
        this.explanation = Objects.requireNonNull(explanation, "Explanation of the word can'readPdf be null");
        this.examples = Objects.requireNonNull(examples, "Examples can'readPdf be null");
    }

    public String getWord() {
        return word;
    }

    public String getExplanation() {
        return explanation;
    }

    public List<String> getExamples() {
        return examples;
    }

    @Override
    public String toString() {
        return "WordMeaning{" +
                "word='" + word + '\'' +
                ", explanation='" + explanation + '\'' +
                ", examples=" + examples +
                '}';
    }
}

