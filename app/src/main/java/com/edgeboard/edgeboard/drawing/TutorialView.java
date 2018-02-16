package com.edgeboard.edgeboard.drawing;

import android.content.Context;
import android.os.Vibrator;

import com.edgeboard.edgeboard.TextToSpeechUtils;

/**
 * Created by Krystian on 2018-02-16.
 */

public class TutorialView extends BaseWritingView {

    public TutorialView(Context context) {
        super(context);
    }

    public TutorialView(Context context, Vibrator vibrator, TextToSpeechUtils ttsUtils) {
        super(context, vibrator, ttsUtils);
    }

    @Override
    public void handleEndOfSequence() {

    }

    @Override
    protected void handleWriting() {

    }
}
