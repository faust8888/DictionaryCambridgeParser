package com.faust8888.cambridge.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DictionaryEvent extends CambridgeEvent {

    @JsonProperty("id")
    private String eventUUID;

    private String tag;

    private String dictionaryName;

    private Date createDate;

    private String userName;

    private DictionaryEvent(final String eventUUID,
                            final String tag,
                            final String dictionaryName,
                            final Date createDate,
                            final String userName) {
        this.eventUUID = eventUUID;
        this.tag = tag;
        this.dictionaryName = dictionaryName;
        this.createDate = createDate;
        this.userName = userName;
    }

    public DictionaryEvent(){}

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    public String getTag() {
        return tag;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DictionaryEvent [");
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {

        private String eventUUID;

        private String tag;

        private String dictionaryName;

        private Date createDate;

        private String userName;

        public Builder addEventId(final String eventUUID) {
            this.eventUUID = eventUUID;
            return this;
        }

        public Builder addTag(final String tag) {
            this.tag = tag;
            return this;
        }

        public Builder addDictionaryName(final String dictionaryName) {
            this.dictionaryName = dictionaryName;
            return this;
        }

        public Builder addCreateDate(final Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder addUserName(final String userName) {
            this.userName = userName;
            return this;
        }

        public DictionaryEvent build() {
            DictionaryEvent event = new DictionaryEvent(eventUUID, tag, dictionaryName, createDate, userName);
            return event;
        }
    }

}
