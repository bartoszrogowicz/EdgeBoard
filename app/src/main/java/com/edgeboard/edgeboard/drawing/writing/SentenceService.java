package com.edgeboard.edgeboard.drawing.writing;

import com.edgeboard.edgeboard.TextToSpeechUtils;
import com.edgeboard.edgeboard.drawing.domain.Alphabet;

/**
 * Created by krystian on 16.02.18.
 */

class SentenceService {

    StringBuilder sentence = new StringBuilder();
    private TextToSpeechUtils ttsUtils;

    private boolean capitalize = false;

    SentenceService(TextToSpeechUtils ttsUtils) {
        this.ttsUtils = ttsUtils;
    }

    void updateSentenceAndReadText(Alphabet alphabetChar) {
        switch(alphabetChar) {
            case CLEAR:
                clearSentence();
                ttsUtils.readText("Clear.");
                break;
            case BACK_SPACE:
                deleteLastLetterFromSentence();
                ttsUtils.readText("Back space.");
                break;
            case CAPITALIZE:
                capitalize = !capitalize;
                ttsUtils.readText("Capitalize.");
                break;
            case SPEAK:
                ttsUtils.readText(sentence.toString());
                break;
            case NOT_A_LETTER:
                break;
            case SPACE:
                ttsUtils.readText("Space.");
                writeLetterToSentence(alphabetChar);
                break;
            default:
                writeLetterToSentence(alphabetChar);
                ttsUtils.readLetter(alphabetChar.getLetter());
                break;
        }
    }

    private void clearSentence() {
        sentence.setLength(0);
    }

    private void deleteLastLetterFromSentence() {
        sentence.setLength(sentence.length() - 1);
    }

    private void writeLetterToSentence(Alphabet alphabetChar) {
        Character letter = alphabetChar.getLetter();
        if(capitalize) {
            letter = Character.toUpperCase(letter);
        }
        sentence.append(letter);
    }



}
