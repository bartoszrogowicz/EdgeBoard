package com.edgeboard.edgeboard.drawing.tutorial;

import android.os.Vibrator;
import android.widget.EditText;

import com.edgeboard.edgeboard.TextToSpeechUtils;
import com.edgeboard.edgeboard.drawing.domain.Alphabet;
import com.edgeboard.edgeboard.drawing.domain.CornerType;
import com.edgeboard.edgeboard.drawing.domain.LearningMode;

import java.util.List;

/**
 * Created by krystian on 16.02.18.
 */

class TutorialService  {

    private Vibrator vibrator;
    private TextToSpeechUtils ttsUtils;
    private EditText editText;

    private String currentLearningSentence;
    private int currentLearningCharIndex = 0;

    private LearningMode currentLearningMode = LearningMode.ALPHABET;

    TutorialService(Vibrator vibrator, TextToSpeechUtils ttsUtils, EditText editText) {
        this.vibrator = vibrator;
        this.ttsUtils = ttsUtils;
        this.editText = editText;
    }

    void handleTutorialActions(Alphabet alphabetAction) {
        switch(alphabetAction) {
            case RESET_LEARNING_INDEX:
                currentLearningCharIndex = 0;
                ttsUtils.readText("Write letters again.");
                break;
            case NEXT_MODE:
                currentLearningMode = currentLearningMode.next();
                currentLearningCharIndex = 0;
                ttsUtils.readText(currentLearningMode.getString());
                break;
            default:
                break;
        }
    }

    void compareSequences(List<CornerType> sequence) {
        List<CornerType> currentLearningSequence = getCurrentModeSequence().getSequence();
        if(currentLearningSequence == Alphabet.NOT_A_LETTER.getSequence() || sequence.size() > currentLearningSequence.size()) {
            return;
        }
        for(int i = 0; i < sequence.size(); i++) {
            if(currentLearningSequence.get(i) == sequence.get(i)) {
                if(sequence.size() == currentLearningSequence.size() && i == currentLearningSequence.size() - 1) {
                    charWritingComplete(Alphabet.fromValue(sequence).getLetter());
                }
                correctCornerVibration();
            } else {
                errorVibration();
            }
        }
    }

    private Alphabet getCurrentModeSequence() {
        switch(currentLearningMode) {
            case ALPHABET:
                currentLearningSentence = Alphabet.getAlphabethString();
                break;
            case PANGRAM:
                currentLearningSentence = Alphabet.getPangramString();
                break;
            case TEXT:
                currentLearningSentence = editText.getText().toString();
                break;
        }
        if(currentLearningSentence.isEmpty() || currentLearningSentence.length() <= currentLearningCharIndex){
            return Alphabet.NOT_A_LETTER;
        }
        return Alphabet.getByLetter(currentLearningSentence.charAt(currentLearningCharIndex));
    }

    private void charWritingComplete(char letter) {
        endOfWritingVibration();
        currentLearningCharIndex++;
        ttsUtils.readLetter(letter);
    }

    void updateSentence(String newSentence) {
        currentLearningSentence = newSentence;
    }

    private void errorVibration() {
        vibrator.vibrate(new long[]{0, 200, 100, 200, 100}, -1);
    }

    private void correctCornerVibration() {
        vibrator.vibrate(200);
    }

    private void endOfWritingVibration() {
        vibrator.vibrate(new long[]{0, 100, 100, 100, 100, 100, 100}, -1);
    }

}
