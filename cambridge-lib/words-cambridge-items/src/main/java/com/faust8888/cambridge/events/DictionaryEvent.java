package com.faust8888.cambridge.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DictionaryEvent extends CambridgeEvent {

    @JsonProperty("id")
    private Long eventId;

    private String tag;

    private String dictionaryName;

    private Date createDate;

    private String userName;

    private DictionaryEvent(final Long eventId,
                            final String tag,
                            final String dictionaryName,
                            final Date createDate,
                            final String userName) {
        this.eventId = eventId;
        this.tag = tag;
        this.dictionaryName = dictionaryName;
        this.createDate = createDate;
        this.userName = userName;
    }

    public DictionaryEvent(){}

    public Long getEventId() {
        return eventId;
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

    public static class Builder {

        private Long eventId;

        private String tag;

        private String dictionaryName;

        private Date createDate;

        private String userName;

        public Builder addEventId(final Long eventId) {
            this.eventId = eventId;
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
            DictionaryEvent event = new DictionaryEvent(eventId, tag, dictionaryName, createDate, userName);
            return event;
        }
    }

}
