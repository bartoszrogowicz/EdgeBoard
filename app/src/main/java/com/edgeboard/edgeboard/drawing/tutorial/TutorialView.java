package com.edgeboard.edgeboard.drawing.tutorial;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.edgeboard.edgeboard.R;
import com.edgeboard.edgeboard.TextToSpeechUtils;
import com.edgeboard.edgeboard.drawing.BaseWritingView;

/**
 * Created by Krystian on 2018-02-16.
 */

public class TutorialView extends BaseWritingView {

    private TutorialService tutorialService;
    private EditText editText;

    public TutorialView(Context context) {
        super(context);
    }

    public TutorialView(Context context, Vibrator vibrator, TextToSpeechUtils ttsUtils) {
        super(context, vibrator);
        this.editText = (EditText) ((Activity)context).findViewById(R.id.editText2);
        this.tutorialService = new TutorialService(vibrator, ttsUtils, editText);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                tutorialService.updateSentence(editText.getText().toString());
            }
        });
    }


    @Override
    public void handleEndOfSequence() {
        tutorialService.handleTutorialActions(writingSequenceService.getEnumFromSequence());
        writingSequenceService.clearSequence();
    }

    @Override
    protected void handleWriting() {
        if(!writingSequenceService.isSequenceStopped()) {
            tutorialService.compareSequences(writingSequenceService.sequence);
        }
    }

    @Override
    public void handleStartWriting() {}
}
