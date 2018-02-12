package com.edgeboard.edgeboard.drawing;

/**
 * Created by Krystian on 2017-12-17.
 */
public enum LearningState {
    PANGRAM("pangram"),
    ALPHABET("alphabet"),
    TEXT("text");

    private String value;

    LearningState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
