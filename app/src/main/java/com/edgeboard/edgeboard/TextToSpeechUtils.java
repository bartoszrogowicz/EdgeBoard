package com.edgeboard.edgeboard;


import android.speech.tts.TextToSpeech;

/**
 * Created by bartek on 25.11.2017.
 */

public class TextToSpeechUtils {

    private TextToSpeech tts;

    TextToSpeechUtils(TextToSpeech tts) {
        this.tts = tts;
    }

    public void readLetter(Character letter) {
        String s = "" + letter;
        readText(s);
    }

    public void readText(String textToRead) {
        tts.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null);
    }


}