package com.faust8888.cambridge.cqrs.command.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "cambridge", name = "TRANSLATION")
public class Translation {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    public Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id")
    public Word word;

    @Column(name = "short_meaning")
    public String shortMeaning;

    @Column(name = "form")
    public String form;

    @OneToMany(mappedBy="translation", cascade = CascadeType.ALL)
    public List<Meaning> examples;

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

    public String getShortMeaning() {
        return shortMeaning;
    }

    public void setShortMeaning(String shortMeaning) {
        this.shortMeaning = shortMeaning;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public List<Meaning> getExamples() {
        return examples;
    }

    public void setExamples(List<Meaning> examples) {
        this.examples = examples;
    }


}
