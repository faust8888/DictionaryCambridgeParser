package com.faust8888.cambridge.cqrs.command.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "cambridge", name = "DICTIONARY_CONTENT")
public class DictionaryContent {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(
            name = "word",
            joinColumns = {@JoinColumn(name = "word_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private List<Word> words;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
