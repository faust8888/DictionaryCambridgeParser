package com.faust8888.cambridge.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.faust8888.cambridge.items.words.Word;

public final class WordAddedEvent extends CambridgeEvent {

    @JsonProperty("id")
    private Long eventId;
    private Long dictionaryId;
    private String dictionaryName;
    private Word word;
    private String wordSearch;

    private WordAddedEvent(final Long eventId, final Long dictionaryId, final String dictionaryName, final Word word) {
        this.eventId = eventId;
        this.dictionaryId = dictionaryId;
        this.dictionaryName = dictionaryName;
        this.word = word;
    }

    public WordAddedEvent(){}

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public Word getWord() {
        return word;
    }

    public void setWordSearch(final String wordSearch) {
        this.wordSearch = wordSearch;
    }

    public String getWordSearch() {
        return wordSearch;
    }

    public void setWord(final Word word) {
        this.word = word;
    }

    public static class Builder {

        private Long eventId;

        private Long dictionaryId;

        private String dictionaryName;

        private Word word;

        public Builder addEventId(final Long eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder addDictionaryId(final Long dictionaryId) {
            this.dictionaryId = dictionaryId;
            return this;
        }

        public Builder addDictionaryName(final String dictionaryName) {
            this.dictionaryName = dictionaryName;
            return this;
        }

        public Builder addWord(final Word word) {
            this.word = word;
            return this;
        }

        public WordAddedEvent build() {
            WordAddedEvent event = new WordAddedEvent(eventId, dictionaryId, dictionaryName, word);
            return event;
        }
    }

}
