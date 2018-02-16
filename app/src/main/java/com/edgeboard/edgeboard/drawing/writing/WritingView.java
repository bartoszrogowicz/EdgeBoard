package com.edgeboard.edgeboard.drawing.writing;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.widget.TextView;

import com.edgeboard.edgeboard.R;
import com.edgeboard.edgeboard.TextToSpeechUtils;
import com.edgeboard.edgeboard.drawing.domain.Alphabet;
import com.edgeboard.edgeboard.drawing.BaseWritingView;

/**
 * Created by Krystian on 2018-02-15.
 */

public class WritingView extends BaseWritingView {

    private SentenceService sentenceService;

    private TextView textView;

    public WritingView(Context context) {
        super(context);
    }

    public WritingView(Context context, Vibrator vibrator, TextToSpeechUtils ttsUtils) {
        super(context, vibrator, ttsUtils);
        this.textView = (TextView)((Activity)context).findViewById(R.id.text1);
        this.sentenceService = new SentenceService(ttsUtils);
    }

    public void handleEndOfSequence() {
        // TODO: end timer

        Alphabet alphabetAction = writingSequenceService.getEnumFromSequence();
        sentenceService.updateSentenceAndReadText(alphabetAction);
        updateTextView();
        writingSequenceService.clearSequence();
    }

    private void updateTextView() {
        textView.setText(sentenceService.sentence.toString());
    }

    /**
     * Extending onTouchEvent().
     */
    protected void handleWriting() {

        // TODO: Start timer
    }
}
