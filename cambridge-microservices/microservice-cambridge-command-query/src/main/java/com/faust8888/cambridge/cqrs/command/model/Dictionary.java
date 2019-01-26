package com.faust8888.cambridge.cqrs.command.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "cambridge", name = "DICTIONARY")
public class Dictionary {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "tag")
    private String tag;

    @Column(name = "dictionary_name")
    private String dictionaryName;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "user_id")
    private Long userId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "dictionary_content",
            joinColumns = {@JoinColumn(name = "dictionary_id", nullable = false, updatable = false) },
            inverseJoinColumns = {@JoinColumn(name = "word_id", nullable = false, updatable = false) })
    private Set<Word> words = new HashSet<>();

    public Set<Word> getWords() {
        return this.words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void addWord(final Word word) {
        words.add(word);
    }
}
