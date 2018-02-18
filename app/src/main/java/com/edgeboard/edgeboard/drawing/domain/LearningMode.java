package com.edgeboard.edgeboard.drawing.domain;

/**
 * Created by Krystian on 2017-12-17.
 */
public enum LearningMode {
    PANGRAM("pangram"),
    ALPHABET("alphabet"),
    TEXT("text");

    private final String value;

    LearningMode(String value) {
        this.value = value;
    }

    public String getString() {
        return value;
    }

    public LearningMode next() {
        LearningMode[] vals = values();
        return vals[(this.ordinal()+1) % vals.length];
    }
}
