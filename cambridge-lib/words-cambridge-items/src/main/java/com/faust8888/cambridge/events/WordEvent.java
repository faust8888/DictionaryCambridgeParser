package com.faust8888.cambridge.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.faust8888.cambridge.items.words.Word;
import com.faust8888.cambridge.items.words.WordKindEventEnum;

public final class WordEvent extends CambridgeEvent {

    @JsonProperty("id")
    private String eventUUID;
    private Long dictionaryId;
    private String dictionaryName;
    private Word word;
    private String wordAsString;
    private WordKindEventEnum kindEventEnum;

    private WordEvent(final String eventUUID, final Long dictionaryId, final String dictionaryName, final Word word) {
        this.eventUUID = eventUUID;
        this.dictionaryId = dictionaryId;
        this.dictionaryName = dictionaryName;
        this.word = word;
    }

    public WordEvent(){}

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public Word getWord() {
        return word;
    }

    public void setWordAsString(final String wordAsString) {
        this.wordAsString = wordAsString;
    }

    public String getWordAsString() {
        return wordAsString;
    }

    public void setWord(final Word word) {
        this.word = word;
    }

    public WordKindEventEnum getKindEventEnum() {
        return kindEventEnum;
    }

    public void setKindEventEnum(WordKindEventEnum kindEventEnum) {
        this.kindEventEnum = kindEventEnum;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("WordEvent [");
        if (kindEventEnum != null) {
            sb.append("event = " + kindEventEnum.name());
        }
        if(wordAsString != null) {
            sb.append(", word = " + wordAsString);
        }
        if (dictionaryName != null) {
            sb.append(", dictionaryName = " + dictionaryName);
        }
        if (dictionaryId != null) {
            sb.append(", dictionaryId = " + dictionaryId);
        }
        if(eventUUID != null) {
            sb.append(", eventUUID = " + eventUUID);
        }
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {

        private String eventUUID;

        private Long dictionaryId;

        private String dictionaryName;

        private Word word;

        public Builder addEventId(final String eventUUID) {
            this.eventUUID = eventUUID;
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

        public WordEvent build() {
            WordEvent event = new WordEvent(eventUUID, dictionaryId, dictionaryName, word);
            return event;
        }
    }

}
