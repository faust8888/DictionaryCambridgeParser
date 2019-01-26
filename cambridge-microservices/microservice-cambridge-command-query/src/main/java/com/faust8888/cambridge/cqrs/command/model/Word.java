package com.faust8888.cambridge.cqrs.command.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "cambridge", name = "WORD")
public class Word {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "word")
    private String word;

    @OneToMany(mappedBy="word", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Translation> translations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }
}
