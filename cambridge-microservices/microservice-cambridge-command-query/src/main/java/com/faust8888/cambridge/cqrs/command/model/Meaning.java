package com.faust8888.cambridge.cqrs.command.model;

import javax.persistence.*;

@Entity
@Table(schema = "cambridge", name = "MEANING")
public class Meaning {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    public Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "word_id", nullable = false)
    public Word word;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "word_translation_id", nullable = false)
    public Translation translation;

    @Column(name = "explanation")
    public String explanation;

    @Column(name = "examples")
    public String examples;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }


}
