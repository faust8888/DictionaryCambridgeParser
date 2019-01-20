package com.faust8888.cambridge.cqrs.command.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(schema = "cambridge", name = "DICTIONARY")
public class Dictionary {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    public Long id;

    @Column(name = "tag")
    public String tag;

    @Column(name = "dictionary_name")
    public String dictionaryName;

    @Column(name = "create_date")
    public Date createDate;

    @Column(name = "user_id")
    public Long userId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "dictionary_content",
            joinColumns = {@JoinColumn(name = "dictionary_id", nullable = false, updatable = false) },
            inverseJoinColumns = {@JoinColumn(name = "word_id", nullable = false, updatable = false) })
    public Set<Word> words;

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
}
