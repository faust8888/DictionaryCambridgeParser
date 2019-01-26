package com.faust8888.cambridge.items.words;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum WordKindEventEnum {

    ADD,
    UPDATE,
    DELETE;

    private static Map<String, WordKindEventEnum> workKindEvents = new HashMap<>(3);

    static {
        workKindEvents.put("add", ADD);
        workKindEvents.put("update", UPDATE);
        workKindEvents.put("delete", DELETE);
    }

    @JsonCreator
    public static WordKindEventEnum forValue(String value) {
        return workKindEvents.get(value.toLowerCase());
    }

    @JsonValue
    public String toValue() {
        for (Map.Entry<String, WordKindEventEnum> entry : workKindEvents.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }
        return null; // or fail
    }

}
